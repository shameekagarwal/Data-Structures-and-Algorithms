# Find Peak Element (2D Matrix)

- https://leetcode.com/problems/find-a-peak-element-ii/
- brute force time complexity - O(4 * m * n) 
- i don't entirely get the intuition visually like in [Find Peak Element](/Step%204:%20Binary%20Search/Step%204.1/Find%20Peak%20Element.md) i.e. the 1d array variant
- however, think like this - max of a column ensures vertically we would not have to check
- eliminating horizontally is the same logic as in 1d array variant

```java
class Solution {

    private int[][] mat;

    public int[] findPeakGrid(int[][] mat) {
        this.mat = mat;
        return solve();
    }

    private int[] solve() {

        int low = 0;
        int high = mat[0].length - 1;
        
        while (low <= high) {
            
            int mid = (low + high) / 2;
            int maxEleRow = findMaxInCol(mid);
            
            boolean isGreaterThanLeft = (mid == 0) || (mat[maxEleRow][mid - 1] < mat[maxEleRow][mid]);
            boolean isGreaterThanRight = (mid == mat[0].length - 1) || (mat[maxEleRow][mid + 1] < mat[maxEleRow][mid]);

            if (isGreaterThanLeft && isGreaterThanRight) {
                return new int[]{maxEleRow, mid};
            } else if (isGreaterThanLeft && !isGreaterThanRight) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new int[]{-1, -1};
    }

    private int findMaxInCol(int col) {
        
        int maxEle = Integer.MIN_VALUE;
        int maxEleRow = -1;
        
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] > maxEle) {
                maxEle = mat[i][col];
                maxEleRow = i;
            }
        }

        return maxEleRow;
    }
}
```
