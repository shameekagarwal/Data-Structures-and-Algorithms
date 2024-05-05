# Right Triangles

- https://leetcode.com/problems/right-triangles/
- consider 1 at every index to be at apex of the right triangle
- then, possibilities = (remaining 1s in the same row) * (remaining 1s in the same col)

```java
class Solution {

    public long numberOfRightTriangles(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowOneCount = new int[m];
        int[] colOneCount = new int[n];
        
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                
                if (grid[i][j] == 1) {
                    rowOneCount[i] += 1;
                    colOneCount[j] += 1;
                }
            }
        }
        
        long result = 0;
        
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                
                if (grid[i][j] == 1) {
                    
                    int otherOnesInSameRow = rowOneCount[i] - 1;
                    int otherOnesInSameCol = colOneCount[j] - 1;
                    
                    result += (otherOnesInSameRow * otherOnesInSameCol);
                }
            }
        }
        
        return result;
    }
}
```
