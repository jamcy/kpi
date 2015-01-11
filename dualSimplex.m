function [res P Icb] = dualSimplex(A, b, c, restrictions, max, basis, print, epsilon)
    % *A, b, c - matrices with data
    % *restrictions - vector, with dimensions like c, holding restrictions 
    %   signs: 1(<=), 0(=), -1(>=)
    % *max - shows if maximization problem: true, false
    % *basis - basis selection method: 'random', 'auto', <basis>
    % print -  logging mode: 'none', 'minimal', 'all'
    % epsilon - calculations accuracy: values less than epsilon are counted as
    %   zero
    % Example:
    %   A=[25 36 26; -6 6 6; 21 26 -8];
    %   b=[41; 42; -2];
    %   c=[35; 0; -9];
    % [res P Icb] = dualSimplex(A, b, c, [1 0 -1], true, 'random', 'all', 0.00001);
    
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
        fprintf('1. Convert task to standard form [max c[t]x while Ax=b] where \n');
        fprintf('A=\n');
        disp(A);
        fprintf('c[t]=\n');
        disp(c');
        fprintf('b[t]=\n');
        disp(b');
    end
    
    % 2. Find conjugate basis
    if(~strcmp(print, 'none'))
        fprintf('2. Find conjugate basis\n');
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
        fprintf('3. Find P0=x and Pi decomposition by Pb\n');
        if(strcmp(print, 'all'))
            printDecomposition(x, P, A, b, Pb);
        end
        fprintf('Decomposition result, X=[x0 Pi]:\n')
        disp([x P]);
    end
    
    % 4. Do iteraion steps
    if(~strcmp(print, 'none'))
        fprintf('3. Iterations:\n');
    end
    [x P Icb] = dualIterations(x, P, Icb, c, print, epsilon);
    res = zeros(1, n); 
    for i=1:m
        res(Icb(Icb==Icb(i)))=x(i);
    end
    for i=1:m
        if(restrictions(i)==0 && res(m+i)>0)
            throw(MException('DualSimplex:UnsolvableTask' ,['Optimal solution contains nonzero arfificial variable x' num2str(m+i)]));
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
        if(strcmp(basis, 'random') || strcmp(basis, 'auto'))
            valid = false;
            i=0;
            while(valid == false && i<size(Ic,1))
                if(strcmp(basis, 'random'))
                    i=randi(size(Ic,1));
                elseif(strcmp(basis, 'auto'))
                    i=i+1;
                end
                valid = checkBasis(A, c, Ic(i, :), epsilon);
            end
            Icb=Ic(i, :);
        elseif(strcmp(basis, 'manual'))
            fprintf( '#\t Icb\tvalid\n');
            for i=1:size(Ic, 1)
                valid = checkBasis(A, c, Ic(i, :), epsilon);
                fprintf([num2str(i) '\t' mat2str(Ic(i, :)) '\t' num2str(valid) '\n']);
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
        throw(MException('DualSimplex:InvalidBasis' ,'Basis solution does not satisfy dual task restrictions'));
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
