# Swim in Rising Water

## DSU

- no need to keep track of time - time required to reach is maximum cell in path
- https://leetcode.com/problems/swim-in-rising-water/
- iterate from 0 to n^2-1 and keep calling union on neighbors
- question says that all value in grid are unique and contain values from 0 to n*n - 1
- return result once ultimate parent of 0,0 and n-1,n-1 become the same
- using a visited is a recurring pattern - only call union if the neighbor has already been visited
- TODO: leetCODE is down, run and fix me

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
        int flattenedSize = n * n;

        int[][] coordinateLookup = new int[flattenedSize][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coordinateLookup[grid[i][j]][0] = i;
                coordinateLookup[grid[i][j]][1] = j;
            }
        }

        DisjointSetUnion dsu = new DisjointSetUnion(flattenedSize);
        boolean[] visited = new boolean[flattenedSize];

        for (int i = 0; i < flattenedSize; i++) {
            
            int[] coordinates = coordinateLookup[i];
            int flattenedCoordinate = (coordinates[0] * n) + coordinates[1];
            visited[flattenedCoordinate] = true;
            
            for (int[] direction : directions) {
                
                int neighborCoordinateX = coordinates[0] + direction[0];
                int neighborCoordinateY = coordinates[1] + direction[1];
                int flattenedNeighborCoordinate = (neighborCoordinateX * n) + neighborCoordinateY;

                if (neighborCoordinateX > -1 && neighborCoordinateX < n && neighborCoordinateY > -1 && neighborCoordinateY < n) {
                    if (visited[flattenedNeighborCoordinate]) {
                        dsu.union(flattenedCoordinate, flattenedNeighborCoordinate);
                    }
                }
            }

            if (dsu.findParent(0) == dsu.findParent(n * n - 1)) {
                return i;
            }
        }

        return -1;
    }

    static class DisjointSetUnion {

        private int[] parent;
        private int[] rank;

        DisjointSetUnion(int n) {

            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            rank = new int[n];
        }

        int findParent(int u) {
            if (u == parent[u]) {
                return u;
            }
            parent[u] = findParent(parent[u]);
            return parent[u];
        }

        void union(int u, int v) {
            
            int parentU = findParent(u);
            int parentV = findParent(v);

            if (parentU == parentV) {
                return;
            }

            if (rank[parentU] < rank[parentV]) {
                union(v, u);
            } else {
                parent[parentV] = parentU;
                if (rank[parentU] == rank[parentV]) {
                    rank[parentU] += 1;
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
