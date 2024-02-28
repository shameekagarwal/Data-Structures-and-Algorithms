# Minimum Falling Path Sum

- https://leetcode.com/problems/minimum-falling-path-sum/
- approach in interview - why greedy will not work -> recursive -> overlapping sub problems -> memoization -> tabular + space optimized

```java
class Solution {

    private int[][] matrix;
    private int rows;
    private int cols;

    public int minFallingPathSum(int[][] matrix) {
        
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            min = Math.min(min, minFallingPathSum(0, i));
        }
        return min;
    }

    private int minFallingPathSum(int row, int col) {

        if (row == rows) return 0;

        int min = Integer.MAX_VALUE;

        if (col > 0) {
            min = Math.min(min, minFallingPathSum(row + 1, col - 1) + matrix[row][col]);
        }
        min = Math.min(min, minFallingPathSum(row + 1, col) + matrix[row][col]);
        if (col < cols - 1) {
            min = Math.min(min, minFallingPathSum(row + 1, col + 1) + matrix[row][col]);
        }

        return min;
    }
}
```

- tabular + space optimization

```java
class Solution {

    private int[][] matrix;
    private int rows;
    private int cols;

    public int minFallingPathSum(int[][] matrix) {
        
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;

        int[] prevRow = matrix[0];

        for (int i = 1; i < rows; i++) {
            
            int[] currentRow = new int[cols];
            
            for (int j = 0; j < cols; j++) {

                currentRow[j] = Integer.MAX_VALUE;

                if (j != 0) {
                    currentRow[j] = Math.min(currentRow[j], prevRow[j - 1] + matrix[i][j]);
                }
                currentRow[j] = Math.min(currentRow[j], prevRow[j] + matrix[i][j]);
                if (j != cols - 1) {
                    currentRow[j] = Math.min(currentRow[j], prevRow[j + 1] + matrix[i][j]);
                }
            }
            prevRow = currentRow;
        }

        int min = Integer.MAX_VALUE;
        for (int i : prevRow) {
            min = Math.min(min, i);
        }
        return min;
    }
}
```
