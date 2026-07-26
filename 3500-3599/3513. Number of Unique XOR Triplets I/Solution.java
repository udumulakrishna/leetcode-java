class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Base cases for small n
        if (n <= 2) {
            return n;
        }
        
        // Find the bit length (number of bits needed to represent n)
        int bitLength = 32 - Integer.numberOfLeadingZeros(n);
        
        // Return 2^(bitLength)
        return 1 << bitLength;
    }
}class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        mx <<= 1;

        boolean[] st = new boolean[mx];
        for (int a : nums) {
            for (int b : nums) {
                st[a ^ b] = true;
            }
        }

        int[] s = new int[mx];
        for (int ab = 0; ab < mx; ab++) {
            if (st[ab]) {
                for (int c : nums) {
                    s[ab ^ c] = 1;
                }
            }
        }

        int ans = 0;
        for (int v : s) {
            ans += v;
        }
        return ans;
    }
}