# Largest Magic Square

- https://leetcode.com/problems/largest-magic-square/
- n^2 to calculate all prefix sums
- now, iteration logic - n^4
  - for all length of squares (go from min(m,n) to 1)
  - for all startX, startY combinations
  - verify for all row sums O(n), col sums O(n) and diagonal sums O(1)

```java
class Solution {

    private CalculateDimension calculateRowSum = new CalculateRowSum();
    private CalculateDimension calculateColSum = new CalculateColSum();
    private CalculateDimension calculateLeftDiagonalSum = new CalculateLeftDiagonalSum();
    private CalculateDimension calculateRightDiagonalSum = new CalculateRightDiagonalSum();

    public int largestMagicSquare(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int length = Math.min(m, n);

        int[][] rowSum = calculateRowSum.calculate(grid);
        int[][] colSum = calculateColSum.calculate(grid);
        int[][] leftDiagonalSum = calculateLeftDiagonalSum.calculate(grid);
        int[][] rightDiagonalSum = calculateRightDiagonalSum.calculate(grid);

        for (int i = length; i >= 1; i--) {

            for (int startX = 0; startX + i <= m; startX++) {

                for (int startY = 0; startY + i <= n; startY++) {

                    int endX = startX + i - 1;
                    int endY = startY + i - 1;

                    Set<Integer> distinctSums = new HashSet<>();

                    for (int row = startX; row <= endX; row++) {
                        if (startY == 0) {
                            distinctSums.add(rowSum[row][endY]);
                        } else {
                            distinctSums.add(rowSum[row][endY] - rowSum[row][startY - 1]);
                        }
                    }

                    for (int col = startY; col <= endY; col++) {
                        if (startX == 0) {
                            distinctSums.add(colSum[endX][col]);
                        } else {
                            distinctSums.add(colSum[endX][col] - colSum[startX - 1][col]);
                        }
                    }

                    if (startX == 0 || startY == 0) {
                        distinctSums.add(leftDiagonalSum[endX][endY]);
                    } else {
                        distinctSums.add(leftDiagonalSum[endX][endY] - leftDiagonalSum[startX - 1][startY - 1]);
                    }

                    if (startX == 0 || endY == n - 1) {
                        distinctSums.add(rightDiagonalSum[endX][startY]);
                    } else {
                        distinctSums.add(rightDiagonalSum[endX][startY] - rightDiagonalSum[startX - 1][endY + 1]);
                    }

                    if (distinctSums.size() == 1) {
                        return i;
                    }
                }
            }
        }

        return 0;
    }
}

interface CalculateDimension {

    int[][] calculate(int[][] grid);
}

class CalculateRowSum implements CalculateDimension {

    @Override
    public int[][] calculate(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++) {

            sum[i][0] = grid[i][0];

            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + grid[i][j];
            }
        }

        return sum;
    }
}

class CalculateColSum implements CalculateDimension {

    @Override
    public int[][] calculate(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] sum = new int[m][n];

        for (int j = 0; j < n; j++) {
            sum[0][j] = grid[0][j];
        }

        for (int i = 1; i < m; i++) {

            for (int j = 0; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + grid[i][j];
            }
        }

        return sum;
    }
}

class CalculateLeftDiagonalSum implements CalculateDimension {

    @Override
    public int[][] calculate(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 || j == 0) {
                    sum[i][j] = grid[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j - 1] + grid[i][j];
                }
            }
        }

        return sum;
    }
}

class CalculateRightDiagonalSum implements CalculateDimension {

    @Override
    public int[][] calculate(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 || j == n - 1) {
                    sum[i][j] = grid[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j + 1] + grid[i][j];
                }
            }
        }

        return sum;
    }
}
```
