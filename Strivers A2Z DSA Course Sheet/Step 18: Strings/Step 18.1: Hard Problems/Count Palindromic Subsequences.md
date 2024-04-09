# Count Palindromic Subsequences

- https://www.naukri.com/code360/problems/count-palindromic-subsequences_1062696
- intuition - if left and right match, number of subsequences = fn(left + 1, right - 1) + 1. +1 because we do not pick anything from inside, and just the matched characters for total results of left to right
- regardless of if they match or not, add to results fn(left + 1, right) and fn(left, right - 1)
- however, subtract fn(left + 1, right - 1), because this part is common for both cases

```java
public class Solution {

    private static final int MOD = 1000000007;

    public static int countPalindromicSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        return countPalindromicSubseq(s.toCharArray(), 0, n - 1, dp, seen);
    }

    private static int countPalindromicSubseq(char[] s, int left, int right, int[][] dp, boolean[][] seen) {

        if (left == right) return 1;
        if (left > right) return 0;

        if (seen[left][right]) {
            return dp[left][right];
        }

        int result = 0;

        if (s[left] == s[right]) {
            result = add(1 + result, countPalindromicSubseq(s, left + 1, right - 1, dp, seen));
        }
        result = add(result, countPalindromicSubseq(s, left + 1, right, dp, seen));
        result = add(result, countPalindromicSubseq(s, left, right - 1, dp, seen));
        result = subtract(result, countPalindromicSubseq(s, left + 1, right - 1, dp, seen));

        seen[left][right] = true;
        dp[left][right] = result;

        return result;
    }

    private static int add(int a, int b) {
        return (int) (((long) a + b) % MOD);
    }

    private static int subtract(int a, int b) {
        long diff = ((long) a - b) % MOD;
        return add((int) diff, MOD);
    }
}
```

## Iterative

- for such questions, think like this - to be able to solve for subsequences of length 5, all subsequences up to length 4 must have been solved first

```java
public class Solution {

    private static final int MOD = 1000000007;

    public static int countPalindromicSubseq(String s) {

        int n = s.length();
        int[][] dp = new int[n][n];
        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            
            for (int i = 0; i <= n - len; i++) {
                
                int j = i +  len - 1;
                
                if (arr[i] == arr[j]) {
                    dp[i][j] = add(1, dp[i + 1][j - 1]);
                }
                
                dp[i][j] = add(dp[i][j], dp[i + 1][j]);
                dp[i][j] = add(dp[i][j], dp[i][j - 1]);
                dp[i][j] = subtract(dp[i][j], dp[i + 1][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    private static int add(int a, int b) {
        return (int) (((long) a + b) % MOD);
    }

    private static int subtract(int a, int b) {
        long diff = ((long) a - b) % MOD;
        return add((int) diff, MOD);
    }
}
```
