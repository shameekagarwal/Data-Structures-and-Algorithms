# Pacific Atlantic Water Flow

- https://leetcode.com/problems/pacific-atlantic-water-flow/
- main trick - flipping the condition
- question states - from what cells can we flow into both pacific and atlantic - given we can travel from taller to smaller cell
- how to solve it - multi source bfs
- we start traveling from all the cells adjoining to pacific and atlantic
- then, we travel to all cells that are greater or equal

```java
class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, -1},
        new int[]{0, 1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] isPacificPossible = pacific(heights);
        boolean[][] isAtlanticPossible = atlantic(heights);

        List<List<Integer>> possible = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isPacificPossible[i][j] && isAtlanticPossible[i][j]) {
                    possible.add(List.of(i, j));
                }
            }
        }

        return possible;
    }

    private boolean[][] pacific(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            queue.addLast(new int[]{i, n - 1});
            visited[i][n - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            queue.addLast(new int[]{m - 1, i});
            visited[m - 1][i] = true;
        }

        bfs(heights, visited, queue);

        return visited;
    }

    private boolean[][] atlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            queue.addLast(new int[]{i, 0});
            visited[i][0] = true;
        }

        for (int i = 0; i < n; i++) {
            queue.addLast(new int[]{0, i});
            visited[0][i] = true;
        }

        bfs(heights, visited, queue);

        return visited;
    }

    private void bfs(int[][] heights, boolean[][] visited, Deque<int[]> queue) {

        int m = heights.length;
        int n = heights[0].length;

        while (!queue.isEmpty()) {

            int[] cell = queue.removeFirst();

            for (int[] direction : directions) {

                int x = cell[0] + direction[0];
                int y = cell[1] + direction[1];

                if (x == -1 || x == m || y == -1 || y == n) continue;
                if (visited[x][y]) continue;
                if (heights[x][y] < heights[cell[0]][cell[1]]) continue;

                queue.addLast(new int[]{x, y});
                visited[x][y] = true;
            }
        }
    }
}
```
