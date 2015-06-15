% This file contains example usage of scripts
% It randomly generates tasks and runs algorithm for them, displaying
% iteration count and solution

ITERATION_COUNT = 10;
MATRIX_SIZE=[3 3];
VALUES_RANGE=100;

RESTRICTION_SIGNS = [1 1 1];
MAXIMIZATION = true;
BASIS_MODE='auto';
EXCLUSION='auto';
EQMODE='normal';
MINMODE='invert';
PRINT_MODE='none';
PRECISION = 0.0001;


for i=1:ITERATION_COUNT
    fprintf('Example %d\t========================================================\n', i);
    A = randi(VALUES_RANGE, MATRIX_SIZE(1), MATRIX_SIZE(2))-VALUES_RANGE/2;
    b = randi(VALUES_RANGE, MATRIX_SIZE(1), 1);
    c = randi(VALUES_RANGE, MATRIX_SIZE(2), 1);
    [statusCode result P Icb iterationCount basisCount basisValid] = dualSimplex(A, b, c, RESTRICTION_SIGNS, MAXIMIZATION, BASIS_MODE, EXCLUSION, EQMODE, MINMODE, PRINT_MODE, PRECISION);
    if statusCode==0
        fprintf('Success. iterations: %d\tbasisCount: %d\tvalidBasisCount: %d\n\n', iterationCount, basisCount, basisValid);
    else
        fprintf('Fail.    iterations: %d\tbasisCount: %d\tvalidBasisCount: %d\n\n', iterationCount, basisCount, basisValid);
    end
end