# Minimum Rectangles to Cover Points

- https://leetcode.com/problems/minimum-rectangles-to-cover-points/

```java
class Solution {

    public int minRectanglesToCoverPoints(int[][] points, int w) {
        
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        
        int n = points.length;
        int prevStart = points[0][0];
        int rectangles = 1;
        
        for (int i = 0; i < n; i++) {
            if (prevStart + w < points[i][0]) {
                prevStart = points[i][0];
                rectangles += 1;
            }
        }
        
        return rectangles;
    }
}
```
