# Most Stones Removed with Same Row or Column

- https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
- here stones need not be consecutive - we cant do the matrix bfs we are used to by moving in four directions
- observation - if we try to obtain all components, we can remove (component size - 1) number of nodes from each component
- above can be rewritten as (a - 1) + (b - 1) + ... (h - 1) (say we have components a to h, having sizes a to h)
- we can again rewrite it as (a + b + ... h) - (n) = (total stones - n), where n = number of components
- we use disjoint set union
- to represent rows and columns using same dsu
  - initial size of dsu - max row seen + max col seen + 2
  - a col becomes - col + max row seen + 1
  - a row stays as is
- another note - typically till now, i was calculating number of components like follows - if parent[i] == i, increment number of components by 1
- however, if a stone is not present on a cell, it does not contribute to the components - imagine a grid like this - 
  ```
  0 0 0
  0 1 0
  0 0 0
  ```
- we have 9 components initially and after the union, 8 components according to dsu by the usual logic, but we know that in reality, we have one component only
- so, i use a "used" array as well - only if we call union on a node has it been used - only mark the ultimate parents
- so now, along with checking if `parent[i] == i`, we also check if `used[i] == true`

```java
class Solution {
    
    public int removeStones(int[][] stones) {

        int maxRow = 0;
        int maxCol = 0;

        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        DisjointSetUnion dsu = new DisjointSetUnion(maxRow + maxCol + 2);

        for (int[] stone : stones) {
            dsu.union(stone[0], stone[1] + maxRow + 1);
        }

        return stones.length - dsu.getNumberOfComponents();
    }

    static class DisjointSetUnion {

        private int[] parent;
        private int[] rank;
        private boolean[] used;
        private int n;

        DisjointSetUnion(int n) {

            this.n = n;
            
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            used = new boolean[n];

            rank = new int[n];
        }

        int getNumberOfComponents() {
            
            int numberOfComponents = 0;
            
            for (int i = 0; i < n; i++) {
                if (parent[i] == i && used[i]) {
                    numberOfComponents += 1;
                }
            }

            return numberOfComponents;
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

            if (parentU == parentV) return;

            if (rank[parentU] < rank[parentV]) {
                union(v, u);
            } else {

                used[parentU] = true;

                parent[parentV] = parentU;
                if (rank[parentU] == rank[parentV]) {
                    rank[parentU] += 1;
                }
            }
        }
    }
}
```
