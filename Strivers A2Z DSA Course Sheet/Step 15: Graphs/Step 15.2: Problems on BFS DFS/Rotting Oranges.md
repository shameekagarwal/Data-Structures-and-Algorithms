# Rotting Oranges

- https://leetcode.com/problems/rotting-oranges/
- bfs because neighboring and "levels"
- we can either maintain the time along with the node when pushing to queue
- this way, we push time = time of dequeued element + 1
- otherwise, we use the usual current level technique
- note - here i had to place the rotten and fresh condition because if all elements are 0, then t - 1 is returns -1, when it should have actually returned 0
- note we end up using the matrix itself as visited

```java
class Solution {

    int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public int orangesRotting(int[][] grid) {
        
        grid = createGridCopy(grid);
        Deque<int[]> queue = new ArrayDeque<>();

        boolean containsRottenOranges = false;
        boolean containsFreshOranges = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    containsRottenOranges = true;
                    queue.addLast(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    containsFreshOranges = true;
                }
            }
        }

        if (!(containsFreshOranges || containsFreshOranges)) return 0;

        int t = 0;

        while (!queue.isEmpty()) {
            
            int currentRottenOranges = queue.size();
            t += 1;
            
            for (int i = 0; i < currentRottenOranges; i++) {
                
                int[] coordinates = queue.removeFirst();
                
                for (int[] direction : directions) {
                    int x = coordinates[0] + direction[0];
                    int y = coordinates[1] + direction[1];
                    if (x > -1 && x < grid.length && y > -1 && y < grid[0].length) {
                        if (grid[x][y] == 1) {
                            grid[x][y] = 2;
                            queue.addLast(new int[]{x, y});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }

        return t - 1;
    }

    private int[][] createGridCopy(int[][] grid) {
        int[][] gridCopy = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            gridCopy[i] = Arrays.copyOfRange(grid[i], 0, grid[i].length);
        }
        return gridCopy;
    }
}
```
