function [statusCode res P Icb iterationCount basisCount basisValid] = dualSimplex(A, b, c, restrictions, max, basis, exclusion, eqmode, minmode, print, epsilon)
    % *A, b, c - matrices with data
    % *restrictions - row vector, having size equals to size of column vector b, holding restrictions 
    %   signs: 1(<=), 0(=), -1(>=)
    % *max - shows if maximization problem: true, false
    % *basis - basis selection method: 
    %   'random' - randomly selected
    %   'manual' - prints all indices combinations (n by m), showing whether each is valid and
    %       then user selects combination of choice manually from the list
    %   <basis> - exact basis, provided as vector input argument (indices of P vectors)
    %   'auto' - generates all combinations n by m of basis vector indices and selects first
    %       valid basis from generated
    % *exclusion - mode of exclusion from basis variable selection: 'manual', 'auto'
    % *eqmode - mode of equals sign restrictions treating:
    %   'normal'(standard dual simplex method),'modified'(adding artificial
    %   variables for equals sign restrictions)
    % *minmode - defines the way to solve minimization task:
    %   'invert' - multiply all 'c' components by -1 and solve maximization task
    %   'natural' - solve minimization task "as is"
    % *print -  logging mode: 'none', 'minimal', 'all'
    %   'minimal' - does not prints decomposition of P0,Pi by basis vectors
    % *epsilon - calculations accuracy: values less than epsilon are counted as zero
    %
    % Return values:
    % statusCode - indicates execution result status.
    %   statusCode=0==>success, statusCode!=0==>error
    % res - solution vector
    % Icb - basis indexes vector
    % iterationCount - number of iterations
    % basisCount - number of existing basis vectors
    % basisValid - number of valid basis vectors
    %
    % Example:
    %   A=[25 36 26; -6 6 6; 21 26 -8];
    %   b=[41; 42; -2];
    %   c=[35; 0; -9];
    % [statusCode res P Icb iterationCount basisCount basisValid] = dualSimplex(A, b, c, [1 0 -1], true, 'random', 'auto', 'normal', 'invert', 'all', 0.00001);
    % [statusCode res P Icb iterationCount basisCount basisValid] = dualSimplex(A, b, c, [1 0 -1], false, 'manual', 'manual', 'modified', 'natural', 'minimal', 0.00001);
    if(max)
        text = 'Maximize';
    else
        text = 'Minimize';
    end
    text = [text ' f(x)=cx while Ax <restrictions> b\n'];
    fprintf(text);
    fprintf('c[t]:\n');
    disp(c');
    fprintf('A <restrictions> b:\n');
    for i=1:size(A, 1)
        for j=1:size(A,2)
            fprintf([num2str(A(i,j)) '\t']);
        end
        if(restrictions(i)==1)
            fprintf('<=\t');
        elseif(restrictions(i)==0)
            fprintf('=\t');
        else
            fprintf('>=\t');
        end
        fprintf([num2str(b(i)) '\n']);
    end
    if(ischar(basis))
        fprintf(['\nBasis method:\t' basis '\n']);
    else
        fprintf('\nBasis:\n');
        disp(basis);
    end
    fprintf(['Epsilon:\t' num2str(epsilon) '\n']);
    % 1. Convert task to maximization and to standard form
    if(strcmp(eqmode, 'normal'))
        A= [A zeros(size(A, 1), nnz(restrictions))];
        innz =1;
        for i=1:size(restrictions,2)
            if(restrictions(i)==0)
                continue;
            end
            A(i, size(A,2)-nnz(restrictions)+innz)=restrictions(i);
            innz=innz+1;
        end
    else
        A = [A zeros(size(A,1))];
        for i=1:size(A,1);
            if(restrictions(i)>=0)
                restriction=1;
            else
                restriction=-1;
            end
            A(i, size(A,2)-size(A,1)+i)=restriction;
        end
    end
    [m, n] = size(A);
    res = zeros(1, n);
    P = zeros(m, n);
    iterationCount=0;
    
    if(~max && strcmp(minmode, 'invert'))
        c = c.*-1;
    end
    if(strcmp(eqmode, 'normal'))
        c = [c ; zeros(nnz(restrictions), 1)];
    else
        c = [c ; zeros(m, 1)];
    end
    
    if(~max && strcmp(minmode, 'natural'))
        operation = 'min';
    else
        operation = 'max';
    end
    if(~strcmp(print, 'none'))
        fprintf('\n=============================================================\n');
        fprintf(['1. Converted task to standard form [' operation ' c[t]x while Ax=b]: \n']);
        fprintf('A=\n');
        disp(A);
        fprintf('c[t]=\n');
        disp(c');
    end
    
    % 2. Find conjugate basis
    if(~strcmp(print, 'none'))
        fprintf('\n=============================================================\n');
        fprintf('2. Find conjugate basis:\n');
    end
	[statusCode Pb Icb basisCount basisValid] = conjugateBasis(A, c, basis, print, operation, epsilon);
    if statusCode~=0
        return;
    end
    
    % 3. Decompose P0=b by basis vectors. Build initial table.
	x = Pb\b;
    for i=1:n
        P(:,i) = Pb\A(:, i);
    end
    if(~strcmp(print, 'none'))
        fprintf('\n=============================================================\n');
        fprintf('3. Find P0=x and Pi decomposition by Pb\n');
        if(strcmp(print, 'all'))
            printDecomposition(x, P, A, b, Pb);
        end
        fprintf('Decomposition result, X=[x0 Pi]:\n')
        disp([x P]);
        fprintf('Initial simplex table:\n');
        printSimplexTable(Icb, x, P, c);
    end
    
    % 4. Do iteraion steps
    if(~strcmp(print, 'none'))
        fprintf('\n=============================================================\n');
        fprintf('4. Iterations:\n');
    end
    [statusCode x P Icb iterationCount] = dualIterations(x, P, Icb, c, operation, exclusion, print, epsilon);
    if statusCode ~= 0
        return;
    end
    for i=1:m
        res(Icb(Icb==Icb(i)))=x(i);
    end
    if(strcmp(eqmode, 'modified'))
        for i=1:m
            if(restrictions(i)==0 && res(m+i)>0)
                statusCode=displayMessage(1, ['Optimal solution contains nonzero arfificial variable x' num2str(m+i) '. Task is unsolvable']);
                return;
            end
        end
    end
    if(~strcmp(print, 'none'))
        fprintf('Optimal solution found:\nx*=');
        disp(res);
        fprintf(['f(x*)=' num2str((c')*(res')) '\n']);
    end
end

function [statusCode, Pb, Icb, basisCount, basisValid] = conjugateBasis(A, c, basis, print, operation, epsilon)
    [m, n] = size(A);
    Pb=zeros(0);
    Icb=zeros(0);
    if(ischar(basis)==false)
        if(size(basis, 2)~=m)
            statusCode = displayMessage(1, 'Inconsistent size of user basis');
            return;
        end
        Icb = basis(1, :);
        basisCount = 1;
    else
        Ic = nchoosek(1:n, m);
        basisCount = size(Ic, 1);
        validIc = zeros(basisCount, 1);
        for i=1:size(Ic,1)
            validIc(i)= checkBasis(A, c, Ic(i, :), operation, epsilon);
        end
        basisValid = sum(validIc);
        if(basisValid==0)
            statusCode = displayMessage(1, 'No valid conjugate basis exist. Task is unsolvable');
            return;
        end
        if(strcmp(basis, 'random') || strcmp(basis, 'auto'))
            i=0;
            while(true)
                if(strcmp(basis, 'random'))
                    i=randi(basisCount);
                elseif(strcmp(basis, 'auto'))
                    i=i+1;
                end
                if(validIc(i))
                    break;
                end
            end
            Icb=Ic(i, :);
        elseif(strcmp(basis, 'manual'))
            fprintf( '#\t Icb\tvalid\n');
            for i=1:basisCount
                fprintf([num2str(i) '\t' mat2str(Ic(i, :)) '\t' num2str(validIc(i)) '\n']);
            end
            i = input('Select bais combination number: ');
            Icb = Ic(i, :);
        else
            statusCode = displayMessage(1, 'Unknown basis selection method', print);
            return;
        end
    end
    [valid Pb y restrictionFlags cb] = checkBasis(A, c, Icb, operation, epsilon);
    if(~strcmp(print, 'none'))
        fprintf('Icb:\n');
        disp(Icb);
        fprintf('Pb:\n');
        disp(Pb);
        fprintf('cb:\n');
        disp(cb);
        fprintf('yb[t] (Solution of Pb[t]y=c):\n');
        disp(y');
        fprintf('Restriction flags (A[t]*yb-c):\n');
        disp(restrictionFlags);
    end
    if(~valid)
        statusCode = displayMessage(1, 'Basis solution does not satisfy dual task restrictions. Task is unsolvable.', print);
    else
        statusCode=0;
    end
end

function [valid Pb y restrictionFlags cb] = checkBasis(A, c, Icb, operation, epsilon)
    m=size(A, 1);
    Pb=zeros(m);
    cb=zeros(m, 1);
    for i=1:m
        Pb(:, i) = A(:, Icb(i));
        cb(i) = c(Icb(i));
    end
    y = Pb'\cb;
    restrictionFlags = ((A' * y) - c)';
    for i=1:m
        if(abs(restrictionFlags(i))<epsilon)
            restrictionFlags(i)=0;
        end
    end
    if(strcmp(operation, 'max'))
        valid = all(restrictionFlags>=0);
    else
        valid = all(restrictionFlags<=0);
    end
end

function [] = printDecomposition(x, P, A, b, Pb)
    fprintf('Find decomposstion of x:\n');
    printLinearEquationSystem(Pb, b);
    fprintf('x[t]=');
    disp(x');
    fprintf('Find decomposition of P vectors:\n');
    for i=1:size(A,2)
        printLinearEquationSystem(Pb, A(:, i));
        fprintf(['P' num2str(i) '[t]=']);
        disp(P(:, i)');
    end
end

function [] = printLinearEquationSystem(A, b)
    [m,n]=size(A);
    for i=1:m
        for j=1:n
            fprintf([num2str(A(i,j)) '\t']);
        end
        fprintf(['=\t' num2str(b(i)) '\n']);
    end
end

function [] = printSimplexTable(Icb, x, P, c)
	indexWidth = 4;
	mainWidth = 10;
	[m, n] = size(P);
	totalWidth = (indexWidth+1)*3+(mainWidth+1)*(n+1)+1;
	table = repmat('-', 1, totalWidth);
	table = [table sprintf('\n|%25s|', 'C')];
	for i=1:n
    	table=strcat(table, sprintf('%10d|', c(i)));
	end
	table = [table '\n' repmat('-', 1, totalWidth)];
	table = [table sprintf('\n|%4s|%4s|%4s|%10s|', 'i', 's', 'Cb', 'P0=x')];
	for i=1:n
    	table = strcat(table, sprintf('%10s|', ['P' num2str(i)]));
	end
	table = [table '\n' repmat('-', 1, totalWidth)];
	for i=1:m
    	table = strcat(table, sprintf('\n|%4d|%4d|%4d|%10.4f|', i, Icb(i), c(Icb(i)), x(i)));
    	for j=1:n
        	table=strcat(table, sprintf('%10.4f|', P(i,j)));
    	end
	end
	table = [table '\n' repmat('-', 1, totalWidth) '\n'];
	fprintf(table);
end
 
function [statusCode] = displayMessage(statusCode, message)
        fprintf(strcat('Error: ', message, '\n'));
end
