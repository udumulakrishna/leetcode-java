import java.util.*;

class TrieNode {
    Map<String, TrieNode> children = new HashMap<>();
    boolean deleted = false;
}

public class Solution {

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        Map<String, List<TrieNode>> subtreeToNodes = new HashMap<>();

        // Sort the paths to ensure consistent trie building
        paths.sort((a, b) -> {
            int minLength = Math.min(a.size(), b.size());
            for (int i = 0; i < minLength; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(a.size(), b.size());
        });

        TrieNode root = new TrieNode();
        for (List<String> p : paths) {
            TrieNode node = root;
            for (String s : p) {
                node.children.putIfAbsent(s, new TrieNode());
                node = node.children.get(s);
            }
        }

        buildSubtreeToNodes(root, subtreeToNodes);

        for (List<TrieNode> nodes : subtreeToNodes.values()) {
            if (nodes.size() > 1) {
                for (TrieNode node : nodes) {
                    node.deleted = true;
                }
            }
        }

        constructPath(root, path, ans);
        return ans;
    }

    private String buildSubtreeToNodes(TrieNode node, Map<String, List<TrieNode>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        List<String> keys = new ArrayList<>(node.children.keySet());
        Collections.sort(keys); // Sort keys to maintain consistent ordering
        for (String s : keys) {
            sb.append(s);
            sb.append(buildSubtreeToNodes(node.children.get(s), map));
        }
        sb.append(")");
        String subtree = sb.toString();
        if (!subtree.equals("()")) {
            map.computeIfAbsent(subtree, k -> new ArrayList<>()).add(node);
        }
        return subtree;
    }

    private void constructPath(TrieNode node, List<String> path, List<List<String>> ans) {
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String s = entry.getKey();
            TrieNode child = entry.getValue();
            if (!child.deleted) {
                path.add(s);
                constructPath(child, path, ans);
                path.remove(path.size() - 1);
            }
        }
        if (!path.isEmpty()) {
            ans.add(new ArrayList<>(path));
        }
    }
}
