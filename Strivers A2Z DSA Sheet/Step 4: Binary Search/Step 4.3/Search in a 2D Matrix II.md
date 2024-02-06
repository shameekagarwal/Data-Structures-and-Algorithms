# Search a 2D Matrix II

- https://leetcode.com/problems/search-a-2d-matrix-ii/
- matrix is sorted row wise and column wise
- but unlike in [Search in a 2D Matrix II](./Search%20in%20a%202D%20Matrix%20II.md), `arr[i][m - 1]` is not necessarily > `arr[i + 1][0]`
- brute force - binary search on every row
- brute force time complexity - `O(N * log2m)`
- optimal - we just traverse from top right corner
- at every step, we can either eliminate the current column or the current row
- so, we accordingly move either left or down
- we could have also chosen bottom left corner for this and move up or right
- time complexity - `O(n + m)`

```java
class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col > -1) {
            if (matrix[row][col] > target) {
                col -= 1;
            } else if (matrix[row][col] < target) {
                row += 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
```
