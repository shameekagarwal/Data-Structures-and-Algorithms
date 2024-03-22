# Longest String Chain

- https://leetcode.com/problems/longest-string-chain/
- just a simple [Longest Increasing Subsequence](./Longest%20Increasing%20Subsequence.md) question
- only thing is sort strings by length, and the function `isDistance1` below

```java
class Solution {

    private static final int NO_OF_CHARS = 'z' - 'a' + 1;

    public int longestStrChain(String[] wordsRaw) {

        int n = wordsRaw.length;

        char[][] words = new char[n][];

        for (int i = 0; i < n; i++) {
            words[i] = wordsRaw[i].toCharArray();
        }

        Arrays.sort(words, (a, b) -> a.length - b.length);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (isDistance1(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 0;
        for (int i : dp) {
            result = Math.max(i, result);
        }
        return result;
    }

    private boolean isDistance1(char[] a, char[] b) {

        if (a.length != b.length - 1) return false;

        int n = a.length;
        int ptr = 0;

        while (ptr < n && a[ptr] == b[ptr]) {
            ptr += 1;
        }

        if (ptr == n) return true;

        while (ptr < n && a[ptr] == b[ptr + 1]) {
            ptr += 1;
        }

        return ptr == n;
    }
}
```
