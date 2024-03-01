# Shortest Common Supersequencue

- https://leetcode.com/problems/shortest-common-supersequence/
- take common part only once
- common part = lcs
- so, final = m + n - lcs
- we want to print the final string
- logic is just like [Print Longest Common Subsequence](./Print%20Longest%20Common%20Subsequence.md), but we pick all the cells along the path, not just when the characters are the same
- why it works - when we say `dp[i][j] = dp[i - 1][j]`, we are basically saying "drop j since it does not contribute to the lcs". we are picking that character now since we need supersequence

```java
class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();

        int m = str1Arr.length;
        int n = str2Arr.length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1Arr[i] == str2Arr[j]) {
                    dp[i][j] = ((i == 0 || j == 0) ? 0 : dp[i - 1][j - 1]) + 1;
                } else {
                    dp[i][j] = Math.max(
                        i == 0 ? 0 : dp[i - 1][j],
                        j == 0 ? 0 : dp[i][j - 1]
                    );
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }

        int x = m - 1;
        int y = n - 1;

        StringBuilder sb = new StringBuilder();

        while (x > -1 && y > -1) {
            if (str1Arr[x] == str2Arr[y]) {
                sb.append(str1Arr[x]);
                x -= 1;
                y -= 1;
            } else {
                if (x > 0 && dp[x][y] == dp[x - 1][y]) {
                    sb.append(str1Arr[x]);
                    x -= 1;
                } else if (y > 0 && dp[x][y] == dp[x][y - 1]) {
                    sb.append(str2Arr[y]);
                    y -= 1;
                } else {
                    break;
                }
            }
        }

        while (x > -1) {
            sb.append(str1Arr[x]);
            x -= 1;
        }

        while (y > -1) {
            sb.append(str2Arr[y]);
            y -= 1;
        }

        sb.reverse();

        return sb.toString();
    }
}
```
