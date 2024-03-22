# Minimum Operations to Write the Letter Y on a Grid

- https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
- just brute forced my way

```java
class Solution {

    public int minimumOperationsToWriteY(int[][] grid) {
        
        int n = grid.length;
        
        int zeroCountOnY = 0;
        int oneCountOnY = 0;
        int twoCountOnY = 0;
        
        // left diagonal
        for (int i = 0; i < n / 2; i++) {
            zeroCountOnY += ((grid[i][i] == 0) ? 1 : 0);
            oneCountOnY += ((grid[i][i] == 1) ? 1 : 0);
            twoCountOnY += ((grid[i][i] == 2) ? 1 : 0);
        }
        
        // right diagonal
        for (int i = 0; i < n / 2; i++) {
            zeroCountOnY += (grid[i][n - 1 - i] == 0 ? 1 : 0);
            oneCountOnY += (grid[i][n - 1 - i] == 1 ? 1 : 0);
            twoCountOnY += (grid[i][n - 1 - i] == 2 ? 1 : 0);
        }
        
        // vertical
        for (int i = n / 2; i < n; i++) {
            zeroCountOnY += (grid[i][n / 2] == 0 ? 1 : 0);
            oneCountOnY += (grid[i][n / 2] == 1 ? 1 : 0);
            twoCountOnY += (grid[i][n / 2] == 2 ? 1 : 0);
        }
        
        // -------------------------------------------------------
        
        int zeroCountOutsideY = 0;
        int oneCountOutsideY = 0;
        int twoCountOutsideY = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                zeroCountOutsideY += (grid[i][j] == 0 ? 1 : 0);
                oneCountOutsideY += (grid[i][j] == 1 ? 1 : 0);
                twoCountOutsideY += (grid[i][j] == 2 ? 1 : 0);
            }
        }
        
        zeroCountOutsideY -= zeroCountOnY;
        oneCountOutsideY -= oneCountOnY;
        twoCountOutsideY -= twoCountOnY;
        
        // -------------------------------------------------------
        
        int result = n * n;
        result = Math.min(result, oneCountOnY + twoCountOnY + zeroCountOutsideY + twoCountOutsideY); // 0 1
        result = Math.min(result, oneCountOnY + twoCountOnY + zeroCountOutsideY + oneCountOutsideY); // 0 2
        result = Math.min(result, zeroCountOnY + twoCountOnY + oneCountOutsideY + twoCountOutsideY); // 1 0
        result = Math.min(result, zeroCountOnY + twoCountOnY + zeroCountOutsideY + oneCountOutsideY); // 1 2
        result = Math.min(result, zeroCountOnY + oneCountOnY + oneCountOutsideY + twoCountOutsideY); // 2 0
        result = Math.min(result, zeroCountOnY + oneCountOnY + zeroCountOutsideY + twoCountOutsideY); // 2 1

        return result;
    }
}
```
