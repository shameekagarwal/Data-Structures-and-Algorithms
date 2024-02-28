# Minimum Path Sum

- https://leetcode.com/problems/minimum-path-sum/
- base cases
  - if we reach target, return target
  - if we reach out of bounds, return int_max
- recursive case - min(down, right) + current

```java
class Solution {

    private int[][] grid;
    private int rows;
    private int cols;

    public int minPathSum(int[][] grid) {

        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        return minPathSum(0, 0);
    }

    private int minPathSum(int x, int y) {

        if (x > rows - 1 || y > cols - 1) return Integer.MAX_VALUE;
        if (x == rows - 1 && y == cols - 1) return grid[rows - 1][cols - 1];
        return Math.min(minPathSum(x + 1, y), minPathSum(x, y + 1)) + grid[x][y];
    }
}
```

- space optimized tabular - 

```java
class Solution {

    public int minPathSum(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[] prevRow = new int[cols];

        for (int i = 0; i < rows; i++) {

            int[] currentRow = new int[cols];

            for (int j = 0; j < cols; j++) {

                if (i == 0 && j == 0) {
                    currentRow[0] = grid[i][j];
                    continue;
                }

                int top = i == 0 ? Integer.MAX_VALUE : prevRow[j];
                int left = j == 0 ? Integer.MAX_VALUE : currentRow[j - 1];

                currentRow[j] = Math.min(top, left) + grid[i][j];
            }

            prevRow = currentRow;
        }

        return prevRow[cols - 1];
    }
}
```

