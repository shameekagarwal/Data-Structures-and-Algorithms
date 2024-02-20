# Rotate Image

- https://leetcode.com/problems/rotate-image/
- naive - do it by using an extra matrix
- question clearly states to not use an extra matrix
- so, we do it in place
- assume matrix is as follow - 
  ```
  1 2 3
  4 5 6
  7 8 9
  ```
- step 1 - first transpose the matrix
  ```
  1 4 7
  2 5 8
  3 6 9
  ```
- step 2 - reverse every row
  ```
  7 4 1
  8 5 2
  9 6 3
  ```

```java
class Solution {
    public void rotate(int[][] matrix) {
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                swap(matrix, i, j, j, i);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            reverse(matrix[i]);
        }
    }

    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            swap(arr, i, arr.length - 1 - i);
        }
    }

    private void swap(int[][] matrix, int i, int j, int x, int y) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[x][y];
        matrix[x][y] = temp;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```
