# Swim in Rising Water

## DSU

- no need to keep track of time - time required to reach is maximum cell in path
- https://leetcode.com/problems/swim-in-rising-water/
- iterate from 0 to n^2-1 and keep calling union on neighbors
- question says that all value in grid are unique and contain values from 0 to n*n - 1
- return result once ultimate parent of 0,0 and n-1,n-1 become the same
- using a visited is a recurring pattern - only call union if the neighbor has already been visited

```java
class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public int swimInWater(int[][] grid) {

        int n = grid.length;
        DSU dsu = new DSU(n * n);
        int[][] lookup = new int[n * n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lookup[grid[i][j]][0] = i;
                lookup[grid[i][j]][1] = j;
            }
        }

        for (int i = 0; i < n * n; i++) {

            int x = lookup[i][0];
            int y = lookup[i][1];

            int flattenedCell = (x * n) + y;
            dsu.put(flattenedCell);

            for (int[] direction : directions) {

                int neighborX = x + direction[0];
                int neighborY = y + direction[1];

                if (neighborX == n || neighborX == -1 || neighborY == n || neighborY == -1) continue;

                int neighborFlattenedCell = (neighborX * n) + neighborY;

                if (dsu.parent[neighborFlattenedCell] != -1) {
                    dsu.union(flattenedCell, neighborFlattenedCell);
                }
            }

            if (dsu.parent[0] != -1 && dsu.parent[n * n - 1] != -1) {
                if (dsu.findParent(0) == dsu.findParent(n * n - 1)) {
                    return i;
                }
            }
        }

        return -1;
    }

    static class DSU {

        int[] parent;
        int[] rank;

        DSU(int n) {
            rank = new int[n];
            parent = new int[n];
            Arrays.fill(parent, -1);
        }

        int findParent(int node) {

            if (parent[node] == node) {
                return node;
            }

            parent[node] = findParent(parent[node]);
            return parent[node];
        }

        void put(int x) {
            parent[x] = x;
        }

        void union(int a, int b) {
            
            int parentA = findParent(a);
            int parentB = findParent(b);

            if (parentA == parentB) return;

            if (rank[parentA] < rank[parentB]) {
                union(b, a);
            } else {
                parent[parentB] = parentA;
                if (rank[parentA] == rank[parentB]) {
                    rank[parentA] += 1;
                }
            }
        }
    }
}
```

## Priority Queue

- value to reach up to a node = max value on the path to reach till that node from 0,0
- just pop the minimum value from priority queue every time

```java
class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public int swimInWater(int[][] grid) {
        
        int n = grid.length;

        PriorityQueue<Cell> minHeap = new PriorityQueue<>();
        minHeap.add(new Cell(grid[0][0], 0, 0));

        boolean[][] visited = new boolean[n][n];

        while (true) {

            Cell cell = minHeap.remove();
            visited[cell.x][cell.y] = true;

            if (cell.x == n - 1 && cell.y == n - 1) {
                return cell.value;
            }

            for (int[] direction : directions) {
                
                int neighborX = cell.x + direction[0];
                int neighborY = cell.y + direction[1];

                if (neighborX > -1 && neighborX < n && neighborY > -1 && neighborY < n) {
                    if (!visited[neighborX][neighborY]) {
                        int timeTakenToReachUptoNeighbor = Math.max(cell.value, grid[neighborX][neighborY]);
                        minHeap.add(new Cell(timeTakenToReachUptoNeighbor, neighborX, neighborY));
                    }
                }
            }
        }
    }

    static class Cell implements Comparable<Cell> {
        
        int value;
        int x;
        int y;

        Cell(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cell cell) {
            return value - cell.value;
        }

        @Override
        public String toString() {
            return "Cell(value=" + value + ", x=" + x + ", y=" + y + ")";
        }
    }
}
```
