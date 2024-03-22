# Count Submatrices with Top-Left Element and Sum Less Than k

- https://leetcode.com/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/
- for each cell, store the sum of all elements in grid starting at 0,0 and ending at current cell
- for 1st row and 1t column, it is `grid[i][j]`
- for 1st column, it is `preSum[i - 1][j] + grid[i][j]`
- for 1st row, it is `preSum[i][j - 1] + grid[i][j]`
- for all others, it is `preSum[i][j - 1] + preSum[i - 1][j] - preSum[i - 1][j - 1]` - the last subtraction removes the common part

```java
class Solution {
    
    public int countSubmatrices(int[][] grid, int k) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    // do nothing
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += (grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1]);
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] <= k) {
                    result += 1;
                }
            }
        }
        
        return result;
    }
}
```
