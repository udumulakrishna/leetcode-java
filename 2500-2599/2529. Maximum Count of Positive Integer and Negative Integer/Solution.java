class Solution {
    public int maximumCount(int[] nums) {
		
		/*
        int a = 0, b = 0;
        for (int x : nums) {
            if (x > 0) {
                ++a;
            } else if (x < 0) {
                ++b;
            }
        }
        return Math.max(a, b); 
		*/
		
		int i = Arrays.binarySearch(nums, 1);
        int a = i < 0 ? nums.length - (-i - 1) : nums.length - i;
        int j = Arrays.binarySearch(nums, 0);
        int b = j < 0 ? -j - 1 : j;
        return Math.max(a, b);
    }
}