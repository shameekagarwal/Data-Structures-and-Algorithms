# Distinct Subsequences

- https://leetcode.com/problems/distinct-subsequences/
- recursive 
  - move 1 step from source by skipping
  - if matches, move target by one as well
- think about the example babgbag and bag
- we say either match the first b in boh, or skip the first b in source to match a potential b later (e.g. 3rd position)V
- base cases 
  - target length reached - return 1
  - source length reached - return 0

```java
class Solution {

    private char[] source;
    private char[] target;
    private int m;
    private int n;

    public int numDistinct(String s, String t) {

        source = s.toCharArray();
        target = t.toCharArray();

        m = source.length;
        n = target.length;

        return numDistinct(0, 0);
    }

    private int numDistinct(int i, int j) {
        
        if (j == n) return 1;
        if (i == m) return 0;

        int sum = numDistinct(i + 1, j);

        if (source[i] == target[j]) {
            sum += numDistinct(i + 1, j + 1);
        }

        return sum;
    }
}
```

- memoization + recursion
- time complexity - O(m*n)

```java
class Solution {

    private char[] source;
    private char[] target;
    private int m;
    private int n;
    private int[][] memo;
    private boolean[][] seen;

    public int numDistinct(String s, String t) {

        source = s.toCharArray();
        target = t.toCharArray();

        m = source.length;
        n = target.length;

        memo = new int[m][n];
        seen = new boolean[m][n];

        return numDistinct(0, 0);
    }

    private int numDistinct(int i, int j) {

        if (j == n) return 1;
        if (i == m) return 0;

        if (seen[i][j]) return memo[i][j];

        memo[i][j] = numDistinct(i + 1, j);

        if (source[i] == target[j]) {
            memo[i][j] += numDistinct(i + 1, j + 1);
        }

        seen[i][j] = true;

        return memo[i][j];
    }
}
```

- tabular + space optimized
- why this weird Math.min limit for j loop - so for e.g. source = ddd, target = dd
- if i try just doing j < n, output is - 
  ```
  [1, 1]
  [2, 2]
  [3, 4]
  ```
- 2nd j of target is matching 1st of source, thus giving 1
- this is not possible
- so, by setting limit like that, we tell that if we are at i of source, we can only match from 0toi of target, not beyond that
- a d at the second position of source cannot match a d at the third position of target

```java
class Solution {

    public int numDistinct(String s, String t) {

        char[] source = s.toCharArray();
        char[] target = t.toCharArray();

        int m = source.length;
        int n = target.length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < Math.min(i + 1, n); j++) {

                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }

                if (source[i] == target[j]) {
                    dp[i][j] += ((i == 0 || j == 0) ? 1 : dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
```
