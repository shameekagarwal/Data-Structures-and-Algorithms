# Find the Largest Area of Square Inside Two Rectangles

- https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles/

```java
class Solution {

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        
        int n = bottomLeft.length;
        
        long result = 0;
        
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {
                
                int commonBottomX = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int commonBottomY = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                
                int commonTopX = Math.min(topRight[i][0], topRight[j][0]);
                int commonTopY = Math.min(topRight[i][1], topRight[j][1]);
                
                if (commonBottomX < commonTopX && commonBottomY < commonTopY) {
                    long side = Math.min(commonTopX - commonBottomX, commonTopY - commonBottomY);
                    result = Math.max(result, side * side);
                }
            }
        }
        
        return result;
    }
}
```
