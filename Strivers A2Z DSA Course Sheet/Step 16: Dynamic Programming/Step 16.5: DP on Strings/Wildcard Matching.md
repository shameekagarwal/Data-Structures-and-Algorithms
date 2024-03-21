# Wildcard Matching

- https://leetcode.com/problems/wildcard-matching/
- `?` - just take it to match
- `*` - is the tricky part
- think case - `ab*cd` and `abxyzcd`
- for * - we can either match nothing i.e. f(i + 1, j), or any number of any characters f(i, j + 1)
- base cases -
  - if we reach target length
    - if all remaining characters in source are * return true
    - else return false
  - if we each source length return false
- recursion time complexity - O(2^n*2^m)
- memoized + recursion - O(m*n)

```java
class Solution {

    private char[] pattern;
    private char[] string;

    private boolean[][] memo;
    private boolean[][] seen;

    public boolean isMatch(String s, String p) {
        
        pattern = p.toCharArray();
        string = s.toCharArray();

        seen = new boolean[pattern.length + 1][string.length + 1];
        memo = new boolean[pattern.length + 1][string.length + 1];
        
        return isMatch(0, 0);
    }

    private boolean isMatch(int idxPattern, int idxString) {

        if (seen[idxPattern][idxString]) {
            return memo[idxPattern][idxString];
        }

        if (idxString == string.length) {
            memo[idxPattern][idxString] = true;
            for (int i = idxPattern; i < pattern.length; i++) {
                if (pattern[i] != '*') {
                    memo[idxPattern][idxString] = false;
                }
            }
        } else if (idxPattern == pattern.length) {
            memo[idxPattern][idxString] = false;
        } else {
            if (pattern[idxPattern] == string[idxString]) {
                memo[idxPattern][idxString] = isMatch(idxPattern + 1, idxString + 1);
            } else if (pattern[idxPattern] == '?') {
                memo[idxPattern][idxString] = isMatch(idxPattern + 1, idxString + 1);
            } else if (pattern[idxPattern] == '*') {
                memo[idxPattern][idxString] = isMatch(idxPattern + 1, idxString) || isMatch(idxPattern, idxString + 1);
            } else {
                memo[idxPattern][idxString] = false;
            }
        }

        seen[idxPattern][idxString] = true;
        return memo[idxPattern][idxString];
    }
}
```

- tabular + space optimized
- do take one extra row and column just for this one in the strings section - makes the problem much easier

```java
class Solution {

    public boolean isMatch(String s, String p) {

        char[] source = s.toCharArray();
        char[] pattern = p.toCharArray();

        int m = source.length;
        int n = pattern.length;

        boolean[][] dp = new boolean[2][n + 1];

        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            if (pattern[i - 1] != '*') break;
            dp[0][i] = true;
        }

        // System.out.println(Arrays.toString(dp[0]));

        for (int i = 1; i <= m; i++) {

            int row = i & 1;
            int previousRow = 1 - row;

            dp[row][0] = false;

            for (int j = 1; j <= n; j++) {
                if (source[i - 1] == pattern[j - 1]) {
                    dp[row][j] = dp[previousRow][j - 1];
                } else if (pattern[j - 1] == '?') {
                    dp[row][j] = dp[previousRow][j - 1];
                } else if (pattern[j - 1] == '*') {
                    dp[row][j] = dp[previousRow][j] || dp[row][j - 1];   
                } else {
                    dp[row][j] = false;
                }
            }

            // System.out.println(Arrays.toString(dp[row]));
        }

        return dp[m & 1][n];
    }
}
```
