function [res P Icb] = dualSimplex(A, b, c, basis, printSteps, max)
    % dualSimplex(A, b, c, 1, true, true) - autogenerate initial basis,
    % show calculation steps, maximization task
    % dialSimplex(A, b, c, 2, false, false) - select initial basis
    % manually, don't show calculation steps, minimization task
    % dualSimplex(A, b, c, [1 2 3], true, true) - basis, input by user
    
    % 1. Convert task to standard form
	A = [A diag(ones(1, size(A,1)))];
    if(~max)
        c = c.*-1;
    end
	c = [c ; zeros(rank(A), 1)];
    if(printSteps)
        fprintf('1.1. Converted task to standard form [max c[t]x while Ax=b] where \n');
        fprintf('A=\n');
        disp(A);
        fprintf('c[t]=\n');
        disp(c');
        fprintf('b[t]=\n');
        disp(b');
        fprintf('1.2. Dual task is [min b[t]y while A[t]y>=c]\n');
    end
    
    % 2. Find conjugate basis
    fprintf('2. Find conjugate basis\n');
	[Pb, Icb] = conjugateBasis(A, c, basis, printSteps);
    
    % 3. Decompose P0=b by basis vectors. Build initial table.
    [m, n] = size(A);
	x = Pb\b;
    P = zeros(m, n);
    for i=1:n
        P(:,i) = Pb\A(:, i);
    end
    if(printSteps)
        fprintf('3. Find P0=x and Pi decomposition by Pb\n');
        fprintf('x0[t]=P0[t]:\n');
        disp(x');
        fprintf('Pi:\n');
        disp(P);
    end
    
    % 4. Do iteraion steps
    if(printSteps)
        fprintf('3. Iterations:\n');
    end
    [x P Icb] = dualIterations(x, P, Icb, c, printSteps);
    res = zeros(1, n); 
    for i=1:m
        res(Icb(Icb==Icb(i)))=x(i);
    end
    if(printSteps)
        fprintf('Optimal solution found:\nx*=');
        disp(res);
    end
end

function [Pb, Icb] = conjugateBasis(A, c, basis, printSteps)
    [m, n] = size(A);
    if(~isscalar(basis))
        if(size(basis, 2)~=m)
            throw(MException('DualSimplex:InconsistentBasisSize' ,'Inconsistent size of user basis'));
        end
        Icb = basis(1, :);
    else
        Ic = nchoosek(1:n, m);
        if(basis==1)
            valid = false;
            while(valid == false)
                Icb = Ic(randi(size(Ic,1)), :);
                [valid Pb y restrictionFlags] = checkBasis(A, c, Icb);
            end
        else
            fprintf( '#\t Icb\tvalid\n');
            for i=1:size(Ic, 1)
                valid = checkBasis(A, c, Ic(i, :));
                fprintf([num2str(i) '\t' mat2str(Ic(i, :)) '\t' num2str(valid) '\n']);
            end
            i = input('Select combination: ');
            Icb = Ic(i, :);
        end
    end
    [valid Pb y restrictionFlags cb] = checkBasis(A, c, Icb);
    if(printSteps)
        fprintf('Icb:\n');
        disp(Icb);
        fprintf('Pb[t]:\n');
        disp(Pb');
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

function [valid Pb y restrictionFlags cb] = checkBasis(A, c, Icb)
    m=size(A, 1);
    Pb=zeros(m);
    cb=zeros(m, 1);
    for i=1:m
   	 Pb(:, i) = A(:, Icb(i));
   	 cb(i) = c(Icb(i));
    end
    y = Pb'\cb;
    restrictionFlags = ((A' * y) - c)';
    valid = all(restrictionFlags>=0);
end
