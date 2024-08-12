# Shortest Common Supersequencue

- https://leetcode.com/problems/shortest-common-supersequence/
- for interviews - do not try to go down the route of optimizing from recursive approach - directly jump to this solution
- take common part only once
- common part = lcs
- so, final = m + n - lcs
- we want to print the final string
- logic is just like [Print Longest Common Subsequence](./Print%20Longest%20Common%20Subsequence.md), but we pick all the cells along the path, not just when the characters are the same
- why it works - when we say `dp[i][j] = dp[i - 1][j]`, we are basically saying "drop i since it does not contribute to the lcs". we are picking that character now since we need supersequence

```java
class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        return shortestCommonSupersequence(str1.toCharArray(), str2.toCharArray());
    }

    public String shortestCommonSupersequence(char[] str1, char[] str2) {

        int m = str1.length;
        int n = str2.length;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);

                if (str1[i] == str2[j]) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j] + 1, dp[i + 1][j + 1]);
                }
            }
        }

        int ptr1 = m;
        int ptr2 = n;

        StringBuilder result = new StringBuilder();

        while (ptr1 > 0 || ptr2 > 0) {

            if (ptr1 > 0 &&dp[ptr1][ptr2] == dp[ptr1 - 1][ptr2]) {
                result.append(str1[ptr1 - 1]);
                ptr1 -= 1;
            } else if (ptr2 > 0 &&dp[ptr1][ptr2] == dp[ptr1][ptr2 - 1]) {
                result.append(str2[ptr2 - 1]);
                ptr2 -= 1;
            } else if (ptr1 > 0 && ptr2 > 0 && dp[ptr1][ptr2] == dp[ptr1 - 1][ptr2 - 1] + 1) {
                result.append(str1[ptr1 - 1]);
                ptr1 -= 1;
                ptr2 -= 1;
            } else {
                System.out.println("kaise hua");
                break;
            }
        }

        result.reverse();

        return result.toString();
    }
}
```
