class Solution {
    public int smallestRepunitDivByK(int k) {
        int n = 1 % k;
        for (int i = 1; i <= k; ++i) {
            if (n == 0) {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        return -1;
    }
}1015. Smallest Integer Divisible by K1015. Smallest Integer Divisible by K