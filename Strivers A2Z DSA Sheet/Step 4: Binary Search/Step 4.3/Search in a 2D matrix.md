# Search in a 2D matrix

- https://leetcode.com/problems/search-a-2d-matrix/
- assume there are n rows and m columns
- brute force - O(n + log2m) - we search for the element in the row only if
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
