# Set Matrix Zeros

- https://leetcode.com/problems/set-matrix-zeroes/
- naive below - with space complexity m + n
  ```java
  class Solution {
      public void setZeroes(int[][] matrix) {
          
          int rows = matrix.length;
          int cols = matrix[0].length;
          
          boolean[] isRowMarkedZero = new boolean[rows];
          boolean[] isColMarkedZero = new boolean[cols];
          
          for (int i = 0; i < rows; i++) {
              for (int j = 0; j < cols; j++) {
                  isRowMarkedZero[i] = isRowMarkedZero[i] || (matrix[i][j] == 0);
                  isColMarkedZero[j] = isColMarkedZero[j] || (matrix[i][j] == 0);
              }
          }
                  
          for (int i = 0; i < rows; i++) {
              for (int j = 0; j < cols; j++) {
                  matrix[i][j] = (isColMarkedZero[j] || isRowMarkedZero[i]) ? 0 : matrix[i][j];
              }
          }
      }
  }
  ```
- optimized - also called "in place"
- do not use extra hash maps like arrays
- make use of the first row / col itself

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        boolean shouldFirstRowBeZero = false;
        boolean shouldFirstColBeZero = false;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) shouldFirstColBeZero = true;
        }

        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) shouldFirstRowBeZero = true;
        }
        
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                matrix[i][j] = (matrix[i][0] == 0 || matrix[0][j] == 0) ? 0 : matrix[i][j];
            }
        }

        if (shouldFirstRowBeZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        if (shouldFirstColBeZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```
