class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1_000_000_007;
        int numValues = r - l + 1;
        
        // dp[v] stores the number of valid sequences ending with value v
        long[] dp = new long[numValues];
        // Initially, for a sequence of length 1, there is exactly 1 way to choose each value
        Arrays.fill(dp, 1L);
        
        // Process from length 2 up to n
        for (int i = 1; i < n; i++) {
            long[] nextDp = new long[numValues];
            
            // If (i % 2 == 1), this step expects an UP transition (current > previous)
            if (i % 2 == 1) {
                long prefixSum = 0;
                for (int v = 0; v < numValues; v++) {
                    nextDp[v] = prefixSum;
                    prefixSum = (prefixSum + dp[v]) % MOD;
                }
            } 
            // If (i % 2 == 0), this step expects a DOWN transition (current < previous)
            else {
                long suffixSum = 0;
                for (int v = numValues - 1; v >= 0; v--) {
                    nextDp[v] = suffixSum;
                    suffixSum = (suffixSum + dp[v]) % MOD;
                }
            }
            dp = nextDp;
        }
        
        // Sum up all combinations for this pattern
        long totalWaysForOnePattern = 0;
        for (long val : dp) {
            totalWaysForOnePattern = (totalWaysForOnePattern + val) % MOD;
        }
        
        // Since the alternating pattern can start either going UP or going DOWN,
        // the total answer is exactly twice the configurations of one pattern.
        return (int) ((totalWaysForOnePattern * 2) % MOD);
    }
}