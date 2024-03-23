# Palindrome Partitioning II

- https://leetcode.com/problems/palindrome-partitioning-ii/
- one state - start
- try partitioning from start to n
- if start to p is a palindrome, call for p + 1

```java
class Solution {

    public int minCut(String s) {
        
        char[] characters = s.toCharArray();
        int n = characters.length;

        boolean[][] isPalindrome = getIsPalindrome(characters, n);
        
        int[] memo = new int[n + 1];
        boolean[] seen = new boolean[n + 1];

        return recurse(0, characters, n, isPalindrome, memo, seen);
    }

    private int recurse(int start, char[] characters, int n, boolean[][] isPalindrome, int[] memo, boolean[] seen) {

        if (seen[start]) {
            return memo[start];
        }

        memo[start] = n - start - 1;

        for (int partition = start; partition < n; partition++) {
            if (isPalindrome[start][partition]) {
                memo[start] = Math.min(memo[start], 1 + recurse(partition + 1, characters, n, isPalindrome, memo, seen));
            }
        }

        seen[start] = true;

        return memo[start];
    }

    private boolean[][] getIsPalindrome(char[] characters, int n) {

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = characters[i] == characters[i + 1];
        }

        for (int len = 3; len <= n; len++) {

            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                isPalindrome[start][end] = (characters[start] == characters[end]) && isPalindrome[start + 1][end - 1];
            }
        }

        return isPalindrome;
    }
}
```

- tabular method - 

```java
class Solution {

    public int minCut(String s) {
    
        char[] characters = s.toCharArray();
        int n = characters.length;

        boolean[][] isPalindrome = getIsPalindrome(characters, n);

        int[] dp = new int[n + 1];
        dp[n] = -1;

        for (int start = n; start > -1; start--) {

            dp[start] = n - start - 1;

            for (int partition = start; partition < n; partition++) {
                if (isPalindrome[start][partition]) {
                    dp[start] = Math.min(dp[partition + 1] + 1, dp[start]);
                }
            }
        }

        return dp[0];
    }

    private boolean[][] getIsPalindrome(char[] characters, int n) {

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = characters[i] == characters[i + 1];
        }

        for (int len = 3; len <= n; len++) {

            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                isPalindrome[start][end] = (characters[start] == characters[end]) && isPalindrome[start + 1][end - 1];
            }
        }

        return isPalindrome;
    }
}
```
