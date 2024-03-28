# Making A Large Island

- https://leetcode.com/problems/making-a-large-island/
- an edge case to remember - surroundings can be part of the same group, just like [here](./Number%20of%20Islands%20II.md)
- however there, we could perform a union which would handle everything for us unlike here
- basically, because of this case below, we cannot simply add "sizes of ultimate parents" of all 4 directions, because they are all part of the same component
- we would end up doing 8 + 8 + 8 + 8 + 1, when we should have just done 8 + 1
  ```
  1 1 1
  1 0 1
  1 1 1
  ```
- so, we simply "insert ultimate parents" of adjacent nodes into a set
- then, ultimate parents will be automatically deduped, and we will just need the size of them
- also possible - largest island without converting any 0s
- note - we do not use all four directions when finding initial groups - just i-1,0 and i,j-1
- this helps us get rid of complexity around visited etc

```java
class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public int largestIsland(int[][] grid) {

        int n = grid.length;
        int sz = n * n;

        DSU dsu = new DSU(n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    int flattenedCoordinate = getFlattenedCoordinate(i, j, n);

                    if (i != 0 && (grid[i - 1][j] == 1)) {
                        int neighboringFlattenedCoordinate = getFlattenedCoordinate(i - 1, j, n);
                        dsu.union(neighboringFlattenedCoordinate, flattenedCoordinate);
                    }

                    if (j != 0 && (grid[i][j - 1] == 1)) {
                        int neighboringFlattenedCoordinate = getFlattenedCoordinate(i, j - 1, n);
                        dsu.union(neighboringFlattenedCoordinate, flattenedCoordinate);
                    }
                }
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                int flattenedCoordinate = getFlattenedCoordinate(i, j, n);
                
                if (grid[i][j] == 1) {
                    result = Math.max(result, dsu.getSize(dsu.findParent(flattenedCoordinate)));
                } else {

                    int sizeAfterJoin = 1;

                    Set<Integer> usedIslands = new HashSet<>();

                    for (int[] direction : directions) {

                        int x = i + direction[0];
                        int y = j + direction[1];

                        if (x == -1 || x == n || y == -1 || y == n) continue;
                        if (grid[x][y] != 1) continue;

                        int neighboringFlattenedCoordinateParent = dsu.findParent(getFlattenedCoordinate(x, y, n));
                        if (usedIslands.contains(neighboringFlattenedCoordinateParent)) continue;
                        usedIslands.add(neighboringFlattenedCoordinateParent);

                        sizeAfterJoin += dsu.getSize(neighboringFlattenedCoordinateParent);
                    }

                    result = Math.max(result, sizeAfterJoin);
                }
            }
        }

        return result;
    }

    private int getFlattenedCoordinate(int x, int y, int n) {
        return (x * n) + y;
    }

    static class DSU {

        private int n;
        private int[] parent;
        private int[] size;

        DSU(int n) {

            this.n = n;
            
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int getSize(int x) {
            return size[x];
        }

        int findParent(int x) {

            if (parent[x] == x) {
                return x;
            }

            parent[x] = findParent(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {

            int parentX = findParent(x);
            int parentY = findParent(y);

            if (parentX == parentY) return;

            if (size[parentX] < size[parentY]) {
                union(y, x);
            } else {
                parent[parentY] = parentX;
                size[parentX] += size[parentY];
            }
        }
    }
}
```
