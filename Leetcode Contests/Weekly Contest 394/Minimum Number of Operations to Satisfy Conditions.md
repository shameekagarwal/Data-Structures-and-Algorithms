# Minimum Number of Operations to Satisfy Conditions

- https://leetcode.com/problems/minimum-number-of-operations-to-satisfy-conditions/
- first, construct `columnLookup` - for a column c, how many occurrences of a number x are there
- then, for every value from 0 to 9, try finding minimum number of operations required to transform column c to this number
- note - we cannot use the same value as used in c - 1
- return the minimum
- time complexity - 10 * 10 * number of columns

```java
class Solution {

    public int minimumOperations(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] columnLookup = new int[n][10];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                columnLookup[j][grid[i][j]] += 1;
            }
        }
        
        int[][] dp = new int[n][11];
        boolean[][] seen = new boolean[n][11];
        
        return recurse(columnLookup, m, n, 0, -1, dp, seen);
    }
    
    private int recurse(int[][] columnLookup, int m, int n, int column, int prevColumnVal, int[][] dp, boolean[][] seen) {
        
        if (column == n) return 0;
        
        if (seen[column][prevColumnVal + 1]) {
            return dp[column][prevColumnVal + 1];
        }
        
        int result = m * n;
        
        for (int i = 0; i <= 9; i++) {
            if (i == prevColumnVal) continue;
            int changes = recurse(columnLookup, m, n, column + 1, i, dp, seen) + (m - columnLookup[column][i]);
            result = Math.min(result, changes);
        }
        
        seen[column][prevColumnVal + 1] = true;
        dp[column][prevColumnVal + 1] = result;
        
        return result;
    }
}
```
