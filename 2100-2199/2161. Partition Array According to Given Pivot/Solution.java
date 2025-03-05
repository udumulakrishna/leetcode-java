class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> left = new ArrayList();
        List<Integer> pv = new ArrayList();
        List<Integer> right = new ArrayList();

        for (int n: nums){
            if(n < pivot){
                left.add(n);
            } else if(n == pivot){
                pv.add(n);
            } else {
                right.add(n);
            }
        }
        left.addAll(pv);
        left.addAll(right);
		int[] output = new int[left.size()];
		for (int i = 0; i < left.size(); i++)
				output[i] = left.get(i);

		return output;
    }
}



/*class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int k = 0;
        for (int x : nums) {
            if (x < pivot) {
                ans[k++] = x;
            }
        }
        for (int x : nums) {
            if (x == pivot) {
                ans[k++] = x;
            }
        }
        for (int x : nums) {
            if (x > pivot) {
                ans[k++] = x;
            }
        }
        return ans;
    }
} */