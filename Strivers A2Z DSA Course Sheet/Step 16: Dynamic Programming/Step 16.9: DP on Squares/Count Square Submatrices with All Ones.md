# Count Square Submatrices with All Ones

- https://leetcode.com/problems/count-square-submatrices-with-all-ones/
- a cell contains all the squares it is the "right bottom" of
- formula - `min([i,j-1], [i-1,j], [i-1,j-1]) + 1`

```java
class Solution {

    public int countSquares(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        int result = 0;

        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            result += dp[i][0];
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = matrix[0][i];
            result += dp[0][i];
        }

        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    result += dp[i][j];
                }
            }
        }

        // for (int[] row : dp) {
        //     System.out.println(Arrays.toString(row));
        // }

        return result;
    }

    private int min(int... a) {

        int result = a[0];

        for (int i : a) {
            result = Math.min(result, i);
        }

        return result;
    }
}
```
