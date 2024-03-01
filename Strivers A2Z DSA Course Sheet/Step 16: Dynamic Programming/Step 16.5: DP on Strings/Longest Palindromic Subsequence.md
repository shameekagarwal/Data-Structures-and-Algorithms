# Longest Palindromic Subsequence

- https://leetcode.com/problems/longest-palindromic-subsequence/
- brute force - generate all subsequences - O(2^n), then pick the longest subsequence
- reverse string
- find [Longest Common Subsequence](./Longest%20Common%20Subsequence.md)

```java
class Solution {

    public int longestPalindromeSubseq(String s) {
        
        char[] s1 = s.toCharArray();
        char[] s2 = s.toCharArray();
        reverse(s2);

        int n = s1.length;
        int[] previous = new int[n];

        for (int i = 0; i < n; i++) {

            int[] current = new int[n];

            for (int j = 0; j < n; j++) {
                if (s1[i] == s2[j]) {
                    current[j] = (j == 0 ? 0 : previous[j - 1]) + 1;
                } else {
                    current[j] = Math.max(
                        j == 0 ? 0 : current[j - 1],
                        i == 0 ? 0 : previous[j]
                    );
                }
            }

            previous = current;
        }

        return previous[n - 1];
    }

    private void reverse(char[] s) {
        
        int n = s.length;

        for (int i = 0; i < n / 2; i++) {
            char temp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = temp;
        }
    }
}
```
