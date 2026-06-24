public class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        if (n == 1) {
            return (r - l + 1) % MOD;
        }

        int m = r - l + 1;
        int size = 2 * m;

        // 1. Construct the transition matrix M of size (2m x 2m)
        long[][] M = new long[size][size];
        for (int x = 0; x < m; x++) {
            // dp[0][x] comes from dp[1][y] where y > x
            for (int y = x + 1; y < m; y++) {
                M[x][m + y] = 1;
            }
            // dp[1][x] comes from dp[0][y] where y < x
            for (int y = 0; y < x; y++) {
                M[m + x][y] = 1;
            }
        }

        // 2. Perform matrix exponentiation M^(n-1)
        long[][] transitionMatrix = matrixPower(M, n - 1);

        // 3. Initial vector V1 filled with 1s (each value has 1 way to start at length 1)
        long[] V1 = new long[size];
        Arrays.fill(V1, 1L);

        // 4. Multiply M^(n-1) by V1 and sum up the results
        long totalWays = 0;
        for (int i = 0; i < size; i++) {
            long currentTerm = 0;
            for (int j = 0; j < size; j++) {
                currentTerm = (currentTerm + transitionMatrix[i][j] * V1[j]) % MOD;
            }
            totalWays = (totalWays + currentTerm) % MOD;
        }

        return (int) totalWays;
    }

    // Binary exponentiation for matrices
    private long[][] matrixPower(long[][] base, int exp) {
        int n = base.length;
        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1; // Identity matrix
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiplyMatrices(result, base);
            }
            base = multiplyMatrices(base, base);
            exp >>= 1;
        }
        return result;
    }

    // Standard matrix multiplication modulo 10^9 + 7
    private long[][] multiplyMatrices(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}