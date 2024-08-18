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

- other solution - 
  - if arr(s) != arr(e) -> f(s,e) = max(f(s+1,e), f(s,e-1))
  - if arr(s) == arr(e) -> also consider f(s+1,e-1) + 2

memoized approach - 

```java
class Solution {

    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseq(s.toCharArray());
    }

    public int longestPalindromeSubseq(char[] s) {
        
        int[][] memo = new int[s.length][s.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return longestPalindromeSubseq(memo, s, 0, s.length - 1);
    }

    public int longestPalindromeSubseq(int[][] memo, char[] s, int start, int end) {

        if (start > end) return 0;
        if (start == end) return 1;

        if (memo[start][end] != -1) {
            return memo[start][end];
        }

        int result = Math.max(
            longestPalindromeSubseq(memo, s, start + 1, end),
            longestPalindromeSubseq(memo, s, start, end - 1)
        );

        if (s[start] == s[end]) {
            result = Math.max(
                result,
                longestPalindromeSubseq(memo, s, start + 1, end - 1) + 2
            );
        }

        memo[start][end] = result;

        return result;
    }
}
```

tabular approach - 

```java
class Solution {

    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseq(s.toCharArray());
    }

    public int longestPalindromeSubseq(char[] s) {
        
        int[][] memo = new int[s.length][s.length];

        for (int i = 0; i < s.length; i++) {
            memo[i][i] = 1;
        }

        for (int i = 0; i < s.length - 1; i++) {
            memo[i][i + 1] = (s[i] == s[i + 1]) ? 2 : 1;
        }

        for (int length = 3; length <= s.length; length++) {

            for (int start = 0; start <= s.length - length; start++) {

                int end = start + length - 1;

                memo[start][end] = Math.max(memo[start + 1][end], memo[start][end - 1]);

                if (s[start] == s[end]) {
                    memo[start][end] = Math.max(memo[start][end], memo[start + 1][end - 1] + 2);
                }
            }
        }

        return memo[0][s.length - 1];
    }
}
```
