# 01 Matrix

- https://leetcode.com/problems/01-matrix/
- fill one level after another, so use bfs
- using the resultant matrix itself as visited, by marking using -1

```java
class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public int[][] updateMatrix(int[][] mat) {

        int[][] result = new int[mat.length][mat[0].length];

        for (int [] row : result) {
            Arrays.fill(row, -1);
        }

        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                    queue.addLast(new int[]{i, j});
                }
            }
        }

        int currentDistance = 1;

        while (!queue.isEmpty()) {

            int currentSize = queue.size();

            for (int i = 0; i < currentSize; i++) {

                int[] cell = queue.removeFirst();

                for (int[] direction : directions) {

                    int x = cell[0] + direction[0];
                    int y = cell[1] + direction[1];

                    if (x > -1 && x < mat.length && y > -1 && y < mat[0].length) {
                        if (result[x][y] == -1) {
                            result[x][y] = currentDistance;
                            queue.addLast(new int[]{x, y});
                        }
                    }
                }
            }

            currentDistance += 1;
        }

        return result;
    }
}
```
