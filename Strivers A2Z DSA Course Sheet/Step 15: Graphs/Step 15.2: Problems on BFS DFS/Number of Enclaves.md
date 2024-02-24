# Number of Enclaves

- https://leetcode.com/problems/number-of-enclaves/

```java
class Solution {

    private static final List<int[]> directions = List.of(
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    );

    public int numEnclaves(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        
        boolean[][] vis = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < rows; i++) {
            
            if (grid[i][0] == 1) {
                vis[i][0] = true;
                queue.add(new int[]{i, 0});
            }
            
            if (cols != 1 && grid[i][cols - 1] == 1) {
                vis[i][cols - 1] = true;
                queue.add(new int[]{i, cols - 1});
            }
        }

        for (int i = 0; i < cols; i++) {
            
            if (grid[0][i] == 1) {
                vis[0][i] = true;
                queue.add(new int[]{0, i});
            }

            if (rows != 1 && grid[rows - 1][i] == 1) {
                vis[rows - 1][i] = true;
                queue.add(new int[]{rows - 1, i});
            }
        }

        while (!queue.isEmpty()) {
            
            int[] current = queue.removeFirst();
            
            for (int[] direction : directions) {
                int[] next = new int[]{current[0] + direction[0], current[1] + direction[1]};
                if (next[0] > -1 && next[0] < rows && next[1] > -1 && next[1] < cols) {
                    if (!vis[next[0]][next[1]] && grid[next[0]][next[1]] == 1) {
                        vis[next[0]][next[1]] = true;
                        queue.addLast(next);
                    }
                }
            }
        }

        int totalEnclaves = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    totalEnclaves += 1;
                }
            }
        }
        return totalEnclaves;
    }
}
```
