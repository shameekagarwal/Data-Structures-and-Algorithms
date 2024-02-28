# Unique Paths II

- https://leetcode.com/problems/unique-paths-ii/
- the only difference from [this](./Unique%20Paths.md) is the existence of new base case - `if (obstacleGrid[row][col] == 1) return 0;`

```java
class Solution {

    private int rows;
    private int cols;
    private int[][] obstacleGrid;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        rows = obstacleGrid.length;
        cols = obstacleGrid[0].length;
        this.obstacleGrid = obstacleGrid;

        return _uniquePathsWithObstacles(0, 0);
    }

    int _uniquePathsWithObstacles(int row, int col) {

        if (row > rows - 1 || col > cols - 1) return 0;
        if (obstacleGrid[row][col] == 1) return 0;
        if (row == rows - 1 && col == cols - 1) return 1;
        return _uniquePathsWithObstacles(row + 1, col) + _uniquePathsWithObstacles(row, col + 1);
    }
}
```

- tabular + space optimized
- note - there was a stupid test case - `[[1]]`, for which 0 was expected as the answer
- so, instead of using - 
  ```java
  if (i == 0 && j == 0) {
      currentRowWays[j] = 1;
      continue;    
  }
  ```
- i used - 
  ```java
  if (i == 0 && j == 0) {
      currentRowWays[j] = 1 - obstacleGrid[i][j];
      continue;    
  }
  ```

```java
class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[] prevRowWays = new int[cols];

        for (int i = 0; i < rows; i++) {

            int[] currentRowWays = new int[cols];

            for (int j = 0; j < cols; j++) {
                
                if (i == 0 && j == 0) {
                    currentRowWays[j] = 1 - obstacleGrid[i][j];
                    continue;    
                }

                int top = i == 0 ? 0 : prevRowWays[j];
                int left = j == 0 ? 0 : currentRowWays[j - 1];
                currentRowWays[j] = (obstacleGrid[i][j] == 1) ? 0 : top + left;
            }

            prevRowWays = currentRowWays;
        }

        return prevRowWays[cols - 1];
    }
}
```
