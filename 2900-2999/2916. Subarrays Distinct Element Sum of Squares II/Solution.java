import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static final int MOD = 1_000_000_007;

    static class SegmentTree {
        int n;
        long[] sum;
        long[] sumSq;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            this.sum = new long[4 * n];
            this.sumSq = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        private void pushDown(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = l + (r - l) / 2;
                apply(2 * node, l, mid, lazy[node]);
                apply(2 * node + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        private void apply(int node, int l, int r, long val) {
            long len = r - l + 1;
            val %= MOD;

            // (c + val)^2 = c^2 + 2 * val * c + val^2
            long term1 = (2 * val % MOD) * sum[node] % MOD;
            long term2 = (val * val % MOD) * len % MOD;
            sumSq[node] = (sumSq[node] + term1 + term2) % MOD;

            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        public void update(int node, int l, int r, int ql, int qr, long val) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            if (ql <= mid) {
                update(2 * node, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(2 * node + 1, mid + 1, r, ql, qr, val);
            }
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        }

        public long getSumSq() {
            return sumSq[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastPos = new HashMap<>();

        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastPos.getOrDefault(nums[i], -1);

            // Add 1 to range [prev + 1, i]
            tree.update(1, 0, n - 1, prev + 1, i, 1);

            // Add the sum of squares of all subarrays ending at index i
            totalSum = (totalSum + tree.getSumSq()) % MOD;

            lastPos.put(nums[i], i);
        }

        return (int) totalSum;
    }
}