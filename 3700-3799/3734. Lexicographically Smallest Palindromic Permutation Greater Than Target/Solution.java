class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Count odd frequency characters
        int oddCount = 0;
        int oddChar = -1;
        for (int c = 0; c < 26; c++) {
            if (freq[c] % 2 != 0) {
                oddCount++;
                oddChar = c;
            }
        }

        // A valid palindrome can have at most 1 odd character count
        if (oddCount > 1) return "";

        // Available characters for the first half of length m = n / 2
        int m = n / 2;
        int[] halfFreq = new int[26];
        for (int c = 0; c < 26; c++) {
            halfFreq[c] = freq[c] / 2;
        }

        // Try prefix lengths L of the half string from m down to 0
        for (int L = m; L >= 0; L--) {
            int[] counts = halfFreq.clone();
            boolean prefixValid = true;

            // Check if we can form target[0 ... L-1]
            for (int i = 0; i < L; i++) {
                int c = target.charAt(i) - 'a';
                if (counts[c] > 0) {
                    counts[c]--;
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            // Determine possible candidate character choices at position L
            int startChar = (L < m) ? (target.charAt(L) - 'a' + (L == m ? 0 : 0)) : 0;
            
            // Iterate choices for character at index L
            // If L == m, we don't need a larger char at position m, just check the full palindrome comparison
            int minC = (L < m) ? target.charAt(L) - 'a' + 1 : 0;
            int maxC = (L < m) ? 25 : 0;

            if (L == m) {
                // Construct palindrome directly from current full prefix matching
                String candidate = buildPalindrome(target.substring(0, m), counts, oddChar, n);
                if (candidate != null && candidate.compareTo(target) > 0) {
                    return candidate;
                }
                continue;
            }

            for (int c = minC; c <= 25; c++) {
                if (counts[c] > 0) {
                    counts[c]--;
                    
                    // Build half prefix
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, L));
                    sb.append((char) ('a' + c));
                    
                    // Fill remaining half greedily (ascending)
                    for (int ch = 0; ch < 26; ch++) {
                        while (counts[ch] > 0) {
                            sb.append((char) ('a' + ch));
                            counts[ch]--;
                        }
                    }

                    String candidate = buildPalindrome(sb.toString(), new int[26], oddChar, n);
                    if (candidate != null && candidate.compareTo(target) > 0) {
                        return candidate;
                    }
                    
                    // Backtrack count for next candidate iteration
                    counts = halfFreq.clone();
                    for (int i = 0; i < L; i++) counts[target.charAt(i) - 'a']--;
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String half, int[] remainingHalfCounts, int oddChar, int n) {
        StringBuilder sb = new StringBuilder(half);
        for (int c = 0; c < 26; c++) {
            while (remainingHalfCounts[c] > 0) {
                sb.append((char) ('a' + c));
                remainingHalfCounts[c]--;
            }
        }

        String firstHalf = sb.toString();
        StringBuilder full = new StringBuilder(firstHalf);

        // Add center character for odd lengths
        if (n % 2 != 0 && oddChar != -1) {
            full.append((char) ('a' + oddChar));
        }

        // Mirror the first half to form full palindrome
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            full.append(firstHalf.charAt(i));
        }

        return full.toString();
    }
}