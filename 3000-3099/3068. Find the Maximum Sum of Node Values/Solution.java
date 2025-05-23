/*class Solution {
  public long maximumValueSum(int[] nums, int k, int[][] edges) {
    long maxSum = 0;
    int changedCount = 0;
    int minChangeDiff = Integer.MAX_VALUE;

    for (final int num : nums) {
      maxSum += Math.max(num, num ^ k);
      changedCount += ((num ^ k) > num) ? 1 : 0;
      minChangeDiff = Math.min(minChangeDiff, Math.abs(num - (num ^ k)));
    }

    if (changedCount % 2 == 0)
      return maxSum;
    return maxSum - minChangeDiff;
  }
}
*/
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        for (int x : nums) {
            long tmp = f0;
            f0 = Math.max(f0 + x, f1 + (x ^ k));
            f1 = Math.max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
}