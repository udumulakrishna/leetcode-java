class Trie {
    private MapString, Trie children = new HashMap();
    private int fid = -1;

    public void insert(int fid, String f) {
        Trie node = this;
        String[] ps = f.split();
        for (int i = 1; i  ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.fid = fid;
    }

    public ListInteger search() {
        ListInteger ans = new ArrayList();
        dfs(this, ans);
        return ans;
    }

    private void dfs(Trie root, ListInteger ans) {
        if (root.fid != -1) {
            ans.add(root.fid);
            return;
        }
        for (var child  root.children.values()) {
            dfs(child, ans);
        }
    }
}

class Solution {
    public ListString removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        for (int i = 0; i  folder.length; ++i) {
            trie.insert(i, folder[i]);
        }
        ListString ans = new ArrayList();
        for (int i  trie.search()) {
            ans.add(folder[i]);
        }
        return ans;
    }
}