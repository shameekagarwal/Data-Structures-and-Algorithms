# Edit Distance

- https://leetcode.com/problems/edit-distance/
- we can do remove, replace, insert
- we already saw remove / remove and insert [here](./Delete%20Operation%20for%20Two%20Strings.md)
- when we are at f(i,j), it means find the minimum operations to convert source(0...i) to target(0...j)
- same - f(i+1,j+1)
- replace - f(i+1,j+1) + 1
- remove - f(i+1,j) + 1
- insert - f(i,j+1) + 1
- base cases -
  - if we reach end of target, we need m - i deletions in source to convert
  - if we reach end of source, we need n - j insertions in source to convert
- time complexity - 3^n - 3 possibilities
- memoized time complexity - O(n*m), but auxiliary stack space of O(n+m)

```java
class Solution {

    private char[] source;
    private char[] target;
    private int[][] dp;
    private boolean[][] seen;

    public int minDistance(String word1, String word2) {

        source = word1.toCharArray();
        target = word2.toCharArray();
        dp = new int[source.length][target.length];
        seen = new boolean[source.length][target.length];

        return minDistance(0, 0);
    }

    private int minDistance(int sourceIdx, int targetIdx) {
        
        if (sourceIdx == source.length) {
            return target.length - targetIdx;
        }

        if (targetIdx == target.length) {
            return source.length - sourceIdx;
        }

        if (seen[sourceIdx][targetIdx]) {
            return dp[sourceIdx][targetIdx];
        }

        if (source[sourceIdx] == target[targetIdx]) {
            dp[sourceIdx][targetIdx] = minDistance(sourceIdx + 1, targetIdx + 1);
        } else {
            dp[sourceIdx][targetIdx] = Math.min(
                minDistance(sourceIdx, targetIdx + 1) + 1,
                Math.min(
                    minDistance(sourceIdx + 1, targetIdx) + 1,
                    minDistance(sourceIdx + 1, targetIdx + 1) + 1
                )
            );
        }

        seen[sourceIdx][targetIdx] = true;

        return dp[sourceIdx][targetIdx];
    }
}
```

- tabular
- a boundary case i missed - if source or target length is 0, return 0

```java
class Solution {

    public int minDistance(String word1, String word2) {

        char[] source = word1.toCharArray();
        char[] target = word2.toCharArray();

        int m = source.length;
        int n = target.length;

        if (m == 0) return n;
        if (n == 0) return m;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = source[i] == target[j] ? 0 : 1;
                } else if (i == 0) {
                    dp[i][j] = source[i] == target[j] ? j : dp[i][j - 1] + 1;
                } else if (j == 0) {
                    dp[i][j] = source[i] == target[j] ? i : dp[i - 1][j] + 1;
                } else if (source[i] == target[j]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
```
