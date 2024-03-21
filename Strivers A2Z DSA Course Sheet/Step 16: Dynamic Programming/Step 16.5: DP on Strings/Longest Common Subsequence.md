# Longest Common Subsequence

- https://leetcode.com/problems/longest-common-subsequence/
- subsequence - maintain order, pick / not pick a character
- return longest common subsequence
- brute -
  - generate all subsequences - 2^n
  - compare each of them with each other
- better - "compare on way"
- index x basically denotes string upto index x
- we need to track two different indexes
- f(0, 5) basically means lcs for `string1[0...2]` and `string2[0...5]`
- if characters at both indices match, we shrink both strings, and add 1 - `1 + f(x - 1, y - 1)`
- if not match - we cannot move both pointers - because we might miss out on a comparison - imagine dec and dce
  - 2nd e matches with 3rd e
  - 3rd e matches with 2nd e
  - so, both dc and de are valid lcs
- so, we move just either of both, not both
- so, we do `max(f(x - 1, y), f(x, y - 1))`
- idea is we try omitting either character at x or at y and again try comparing for lcs
- base case - either of the indices go to < 0, return 0
- time complexity - `O(2^m * 2^n)`
- recursive - 

```java
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(0, 0, text1.toCharArray(), text2.toCharArray());
    }

    private int longestCommonSubsequence(int idx1, int idx2, char[] text1, char[] text2) {
        
        if (idx1 == text1.length || idx2 == text2.length) return 0;

        if (text1[idx1] == text2[idx2]) {
            return 1 + longestCommonSubsequence(idx1 + 1, idx2 + 1, text1, text2);
        }

        return Math.max(
            longestCommonSubsequence(idx1 + 1, idx2, text1, text2),
            longestCommonSubsequence(idx1, idx2 + 1, text1, text2)
        );
    }
}
```

- memoized - `O(n*m)`
- overlapping sub problems - look at 4,4
  ```
            5,5

     4,5           5,4
  
  3,5   4,4     4,4   5,3
  ```

```java
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        
        int length1 = text1.length();
        int length2 = text2.length();
        
        int[][] memo = new int[length1][length2];
        boolean[][] seen = new boolean[length1][length2];
        
        return longestCommonSubsequence(0, 0, text1.toCharArray(), text2.toCharArray(), length1, length2, memo, seen);
    }

    private int longestCommonSubsequence(int idx1, int idx2, char[] text1, char[] text2, int length1, int length2, int[][] memo, boolean[][] seen) {
        
        if (idx1 == length1 || idx2 == length2) return 0;

        if (seen[idx1][idx2]) return memo[idx1][idx2];

        if (text1[idx1] == text2[idx2]) {
            memo[idx1][idx2] = 1 + longestCommonSubsequence(idx1 + 1, idx2 + 1, text1, text2, length1, length2, memo, seen);
        } else {
            memo[idx1][idx2] = Math.max(
                longestCommonSubsequence(idx1 + 1, idx2, text1, text2, length1, length2, memo, seen),
                longestCommonSubsequence(idx1, idx2 + 1, text1, text2, length1, length2, memo, seen)
            );
        }

        seen[idx1][idx2] = true;
        return memo[idx1][idx2];
    }
}
```

- tabular + space optimized

```java
class Solution {

    public int longestCommonSubsequence(String text1Str, String text2Str) {

        char[] text1 = text1Str.toCharArray();
        char[] text2 = text2Str.toCharArray();

        int m = text1.length;
        int n = text2.length;

        int[][] dp = new int[2][n];

        for (int i = 0; i < m; i++) {

            int row = i & 1;
            int previousRow = 1 - row;

            for (int j = 0; j < n; j++) {
                if (text1[i] == text2[j]) {
                    if (i == 0 || j == 0) {
                        dp[row][j] = 1;
                    } else {
                        dp[row][j] = dp[previousRow][j - 1] + 1;
                    }
                } else {
                    if (j == 0 && i == 0) {
                        dp[row][j] = 0;
                    } else if (j == 0) {
                        dp[row][j] = dp[previousRow][j];
                    } else if (i == 0) {
                        dp[row][j] = dp[row][j - 1];
                    } else {
                        dp[row][j] = Math.max(dp[row][j - 1], dp[previousRow][j]);
                    }
                }
            }
        }

        return dp[(m - 1) & 1][n - 1];
    }
}
```
