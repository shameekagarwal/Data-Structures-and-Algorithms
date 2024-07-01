# Longest Common Substring

- https://www.codingninjas.com/studio/problems/longest-common-substring_1235207
- like [this](./Longest%20Common%20Subsequence.md), but with minor tweaks
- remember - cannot think of recursive approach here unlike other problems
- longest substring ending at i, j - i-1,j-1 + 1 if `str1[i]` and `str2[j]` are same, else 0
- longest - maintain a separate variable. understand that this time, `dp[m-1][n-1]` will not have the result like in subsequences

```java
public class Solution {

    public static int lcs(String str1, String str2) {

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int m = s1.length;
        int n = s2.length;

        int[][] dp = new int[2][n];
        int result = 0;

        for (int i = 0; i < m; i++) {

            int row = i & 1;
            int previousRow = 1 - row;

            for (int j = 0; j < n; j++) {
                if (s1[i] == s2[j]) {
                    if (i == 0 || j == 0) {
                        dp[row][j] = 1;
                    } else {
                        dp[row][j] = dp[previousRow][j - 1] + 1;
                    }

                    result = Math.max(result, dp[row][j]);
                } else {
                    dp[row][j] = 0;
                }
            }
        }

        return result;
    }
}

```
