class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        var g1 = build(edges1);
        var g2 = build(edges2);
        int n = g1.length, m = g2.length;
        int[] c1 = new int[n];
        int[] c2 = new int[m];
        int[] cnt1 = new int[2];
        int[] cnt2 = new int[2];
        dfs(g2, 0, -1, c2, 0, cnt2);
        dfs(g1, 0, -1, c1, 0, cnt1);
        int t = Math.max(cnt2[0], cnt2[1]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = t + cnt1[c1[i]];
        }
        return ans;
    }
class Solution {
    private int n;
    private List<Integer>[] g;

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        n = edges.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].add(edges[i]);
            }
        }
        int[] d1 = f(node1);
        int[] d2 = f(node2);
        int d = 1 << 30;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int t = Math.max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }

    private int[] f(int i) {
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        dist[i] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        while (!q.isEmpty()) {
            i = q.poll();
            for (int j : g[i]) {
                if (dist[j] == 1 << 30) {
                    dist[j] = dist[i] + 1;
                    q.offer(j);
                }
            }
        }
        return dist;
    }
}
    private List<Integer>[] build(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return g;
    }

    private void dfs(List<Integer>[] g, int a, int fa, int[] c, int d, int[] cnt) {
        c[a] = d;
        cnt[d]++;
        for (int b : g[a]) {
            if (b != fa) {
                dfs(g, b, a, c, d ^ 1, cnt);
            }
        }
    }
}