# Search in a 2D matrix

- https://leetcode.com/problems/search-a-2d-matrix/
- assume there are n rows and m columns
- remember - brute force - O(n + log2m) - we search for the element in the row only if
  ```
  mat[i][0] <= target <= mat[i][m - 1]
  ```
- optimized - think of it like a flattened array
- complexity - O(log2(m * n))

```java
class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {

        int noOfCols = matrix[0].length;
        int noOfRows = matrix.length;
        
        int low = 0;
        int high = (noOfRows * noOfCols) - 1;

        while (low <= high) {
            
            int mid = (low + high) / 2;
            int row = mid / noOfCols;
            int col = mid % noOfCols;
            
            if (matrix[row][col] > target) {
                high = mid - 1;    
            } else if (matrix[row][col] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
```

## Solution 2

- we can also do O(log m + log n)
- first find row that can contain this value in log m, then find the column that can contain it in log n
- note - log ab = log a + log b? so both solutions have same time complexities, they are just different approaches? 

```java
class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        
        int row = findRow(matrix, target);
        if (row == -1) return false;
        
        int col = findIndex(matrix[row], target);
        return col != -1;
    }

    private int findIndex(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    private int findRow(int[][] matrix, int target) {

        int low = 0;
        int high = matrix.length - 1;
        int cols = matrix[0].length;

        while (low <= high) {

            int mid = low + (high - low) / 2;
            
            if (matrix[mid][0] > target) {
                high = mid - 1;
            } else if (matrix[mid][cols - 1] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
```
