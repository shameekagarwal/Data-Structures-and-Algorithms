# Path With Minimum Effort

- https://leetcode.com/problems/path-with-minimum-effort/
- we use dijkstra
- instead of current path + weight, we use max(current path, weight)
- weight = abs(difference in heights)

```java
class Solution {

    private static final List<int[]> directions = List.of(
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    );

    public int minimumEffortPath(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        int[][] efforts = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(efforts[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Height> minHeap = new PriorityQueue<>();
        minHeap.add(new Height(0, 0, 0));
        efforts[0][0] = 0;

        while (!minHeap.isEmpty()) {
            
            Height height = minHeap.poll();

            for (int[] direction : directions) {
                
                int x = height.row + direction[0];
                int y = height.col + direction[1];
                
                if (x < 0 || x > rows - 1 || y < 0 || y > cols - 1) continue;

                int effortForNextStep = Math.abs(heights[height.row][height.col] - heights[x][y]);
                int effortReqdViaCurrentPath = Math.max(height.effort, effortForNextStep);
                if (efforts[x][y] > effortReqdViaCurrentPath) {
                    efforts[x][y] = effortReqdViaCurrentPath;
                    minHeap.add(new Height(x, y, effortReqdViaCurrentPath));
                }
            }
        }

        return efforts[rows - 1][cols - 1];
    }

    static class Height implements Comparable<Height> {

        int row;
        int col;
        int effort;

        Height(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }

        @Override
        public int compareTo(Height height) {
            return effort - height.effort;
        }
    }
}
```
