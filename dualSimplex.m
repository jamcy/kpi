function [res P Icb] = dualSimplex(A, b, c, restrictions, max, basis, exclusion, print, epsilon)
    % *A, b, c - matrices with data
    % *restrictions - row vector, having size equals to size of column vector b, holding restrictions 
    %   signs: 1(<=), 0(=), -1(>=)
    % *max - shows if maximization problem: true, false
    % *basis - basis selection method: 'random', 'auto', 'manual', <basis>
    %   'auto' - generates all combinations n by m of basis vector indices and selects first
    %       valid basis from generated
    % exclusion - mode of exclusion from basis variable selection: 'manual', 'auto'
    % print -  logging mode: 'none', 'minimal', 'all'
    % epsilon - calculations accuracy: values less than epsilon are counted as zero
    % Icb - basis indexes vector
    % Example:
    %   A=[25 36 26; -6 6 6; 21 26 -8];
    %   b=[41; 42; -2];
    %   c=[35; 0; -9];
    % [res P Icb] = dualSimplex(A, b, c, [1 0 -1], true, 'random', 'auto', 'all', 0.00001);
    % [res P Icb] = dualSimplex(A, b, c, [1 0 -1], false, 'manual', 'minimal', 0.00001);
    
    clc;
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
    A = [A zeros(size(A,1))];
    for i=1:size(A,1);
        if(restrictions(i)>=0)
            restriction=1;
        else
            restriction=-1;
        end
        A(i, size(A,2)-size(A,1)+i)=restriction;
    end
    if(~max)
        c = c.*-1;
    end
	c = [c ; zeros(rank(A), 1)];
    if(~strcmp(print, 'none'))
        fprintf('\n=============================================================\n');
        fprintf('1. Convert task to standard form [max c[t]x while Ax=b]: \n');
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
	[Pb, Icb] = conjugateBasis(A, c, basis, print, epsilon);
    
    % 3. Decompose P0=b by basis vectors. Build initial table.
    [m, n] = size(A);
	x = Pb\b;
    P = zeros(m, n);
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
    [x P Icb] = dualIterations(x, P, Icb, c, exclusion, print, epsilon);
    res = zeros(1, n); 
    for i=1:m
        res(Icb(Icb==Icb(i)))=x(i);
    end
    for i=1:m
        if(restrictions(i)==0 && res(m+i)>0)
            throw(MException('DualSimplex:UnsolvableTask' ,['Optimal solution contains nonzero arfificial variable x' num2str(m+i) '. Task is unsolvable']));
        end
    end
    if(~strcmp(print, 'none'))
        fprintf('Optimal solution found:\nx*=');
        disp(res);
    end
end

function [Pb, Icb] = conjugateBasis(A, c, basis, print, epsilon)
    [m, n] = size(A);
    if(ischar(basis)==false)
        if(size(basis, 2)~=m)
            throw(MException('DualSimplex:InconsistentBasisSize' ,'Inconsistent size of user basis'));
        end
        Icb = basis(1, :);
    else
        Ic = nchoosek(1:n, m);
        validIc = zeros(size(Ic,1),1);
        for i=1:size(Ic,1)
            validIc(i)= checkBasis(A, c, Ic(i, :), epsilon);
        end
        if(sum(validIc)==0)
            throw(MException('DualSimplex:NoValidBasis' ,'No valid conjugate basis exist. Task is unsolvable'));
        end
        if(strcmp(basis, 'random') || strcmp(basis, 'auto'))
            i=0;
            while(true)
                if(strcmp(basis, 'random'))
                    i=randi(size(Ic,1));
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
            for i=1:size(Ic, 1)
                fprintf([num2str(i) '\t' mat2str(Ic(i, :)) '\t' num2str(validIc(i)) '\n']);
            end
            i = input('Select bais combination number: ');
            Icb = Ic(i, :);
        else
            throw(MException('DualSimplex:UnknownBasisMethod' ,'Unknown basis selection method'));
        end
    end
    [valid Pb y restrictionFlags cb] = checkBasis(A, c, Icb, epsilon);
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
        throw(MException('DualSimplex:InvalidBasis' ,'Basis solution does not satisfy dual task restrictions. Task is unsolvable.'));
    end
end

function [valid Pb y restrictionFlags cb] = checkBasis(A, c, Icb, epsilon)
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
    valid = all(restrictionFlags>=0);
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
