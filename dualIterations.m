function [x P Icb]= dualIterations(x, P, Icb, c, operation, exclusion, print, epsilon)
    m=size(P, 1);
    iteration = 1;
    while(true)
        if(all(x>=0))
            if(~strcmp(print, 'none'))
                fprintf('\nIteration %d:\n', iteration);
                fprintf('Solution found for table:\n');
                printSimplexTable(Icb, x, P, c);
            end
            return;
        end
        for i=1:m
            if((x(i)<0) && (all(P(i,:)>=0)))
                if(~strcmp(print, 'none'))
                    fprintf('\nIteration %d:\n', iteration);
                    fprintf('Unsolvable task for table:\n');
                    printSimplexTable(Icb, x, P, c);
                end
                throw(MException('DualSimplex:UnsolvableTask' , ['Basis solution does not satisfy restrictions(All elements of s=' num2str(Icb(i)) 'row are >=0 while x' num2str(Icb(i)) '<0). Task is unsolvable.']));
            end
        end
        deltas = calculateDeltas(Icb, P, c, epsilon);
        if(strcmp(exclusion, 'auto'))
            l=Icb(x == (min(x)));
        else
            printSimplexTable(Icb, x, P, c);
            valid = false;
            while(~valid)
                l = input('Select index (s) of variable to exclude from basis. l= ');
                valid = ismember(l, Icb);
                if(~valid)
                    fprintf(['No variable with index ' num2str(l) ' found in basis\n']);
                elseif(x(Icb==l)>=0)
                    ok = input('You have selected non-negative variable to exclude from basis. Are you shure? (1(yes)/0(no)):');
                    valid = (ok==1);
                end
            end
        end
        gammas = calculateGammas(Icb, P, deltas, l, epsilon);
        if(strcmp(exclusion, 'auto'))
            if(strcmp(operation, 'max'))
                r=find(gammas==min(gammas),1);
            else
                maxnegative = min(gammas);
                for i=1:size(gammas, 2)
                    if(gammas(i)<0 && gammas(i)>maxnegative)
                        maxnegative = gammas(i);
                    end
                end
                maxnegative
                r=find(gammas==maxnegative, 1);
            end
        else
            printEstimatesTable(deltas, gammas);
            while(true)
                r=input('Select index of P to include into basis. r=');
                if(ismember(l, Icb))
                    fprintf(['Vector P' num2str(r) ' is already in basis\n']);
                    continue;
                else
                    break;
                end
            end
        end
        if(~exist('r', 'var'))
            throw(MException('DualSimplex:UnsolvableTask' ,'All gammas evaluated to NaN. Can not proceed'));
        end
        if(~strcmp(print, 'none'))
            fprintf('\nIteration %d:\n', iteration);
            printSimplexTable(Icb, x, P, c);
            printEstimatesTable(deltas, gammas);
            fprintf('Excluding l=%d\n', l);
            if(strcmp(operation, 'max'))
                fprintf(['Minimal gamma is ' num2str(min(gammas)) ' ==> Including r=' num2str(r) '\n']);
            else
                fprintf(['Maximal negative gamma is ' num2str(min(gammas)) ' ==> Including r=' num2str(r) '\n']);
            end
            fprintf('Recalculating table...\n');
        end
        [x P Icb] = eliminateGJ(Icb, [x P], l, r, epsilon);
        iteration=iteration+1;
    end
end

function [deltas] = calculateDeltas(Icb, P, c, epsilon)
	[m, n] = size(P);
	deltas = NaN(1, n);
	for j=1:n
    	if(ismember(j, Icb))
        	continue;
    	end
    	delta = 0;
        for i=1:m
            delta = delta+c(Icb(i))*P(i, j);
        end
    	deltas(j)=delta-c(j);
        if(abs(deltas(j))<epsilon)
            deltas(j)=0;
        end
	end
end

function [gammas] = calculateGammas(Icb, P, deltas, l, epsilon)
	n = size(P,2);
	gammas = NaN(1, n);
	for j=1:n
    	if(ismember(j, Icb))
        	continue;
    	end
    	x_lj = P(Icb==l, j);
    	if(x_lj<0)
        	gammas(j)=-deltas(j)/x_lj;
            if(abs(gammas(j))<epsilon)
                gammas(j)=0;
            end
    	end
	end
end

function [x P Icb] = eliminateGJ(Icb, P, l, r, epsilon)
	[m, n] = size(P);
	ilr = find(Icb==l,1);
	jlr = r+1;
	N = P(:,:);
    for i=1:m
        for j=1:n
            if(i==ilr)
                N(i,j) = P(i,j)/P(ilr,jlr);
            else
                N(i,j) = P(i,j)- P(i, jlr) * P(ilr, j)/P(ilr, jlr);
            end
            if(abs(N(i,j))<epsilon)
                N(i,j)=0;
            end
        end
    end
	x=N(:, 1);
	P=N(:, 2:n);
    Icb(Icb==l)=r;
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
 
 function [] = printEstimatesTable(deltas, gammas)
    indexWidth = 4;
	mainWidth = 10;
    n = size(deltas,2);
    totalWidth = (indexWidth+1)*3+(mainWidth+1)*(n+1)+1;
    table = sprintf('|%25s|', 'Deltas');
    for i=1:n
   	 table = strcat(table, sprintf('%10.4f|', deltas(i)));
    end
    table = [table '\n' repmat('-', 1, totalWidth) '\n'];
    table = [table sprintf('|%25s|', 'Gammas')];
    for i=1:n
        table = strcat(table, sprintf('%10.4f|', gammas(i)));
    end
    table=[table '\n' repmat('-', 1, totalWidth) '\n'];
    fprintf(table);
 end