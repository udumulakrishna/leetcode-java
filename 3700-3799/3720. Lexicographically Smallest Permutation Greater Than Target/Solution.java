

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCounts = new int[26];
        for (int i = 0; i < n; i++) {
            totalCounts[s.charAt(i) - 'a']++;
        }

        // Try to match prefix of length L from n-1 down to 0
        for (int L = n - 1; L >= 0; L--) {
            int[] counts = totalCounts.clone();
            boolean prefixValid = true;

            // Check if we can form target[0 ... L-1]
            for (int i = 0; i < L; i++) {
                char ch = target.charAt(i);
                if (counts[ch - 'a'] > 0) {
                    counts[ch - 'a']--;
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            // Find smallest character strictly greater than target[L]
            int targetChar = target.charAt(L) - 'a';
            int choiceChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (counts[c] > 0) {
                    choiceChar = c;
                    break;
                }
            }

            if (choiceChar != -1) {
                // Found valid split index at L
                StringBuilder sb = new StringBuilder();
                sb.append(target.substring(0, L));
                sb.append((char) ('a' + choiceChar));
                counts[choiceChar]--;

                // Append remaining characters in sorted order
                for (int c = 0; c < 26; c++) {
                    while (counts[c] > 0) {
                        sb.append((char) ('a' + c));
                        counts[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}