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
}