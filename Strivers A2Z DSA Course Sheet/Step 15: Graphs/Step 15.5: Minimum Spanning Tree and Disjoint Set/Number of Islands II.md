# Number of Islands II

- https://www.codingninjas.com/studio/problems/largest-island_840701
- represent every cell as a node in the dsu
- formula - (row number * total columns) + column number
- every time we add a node, we first mark it as visited
- then we check for its "visited surroundings"
- every time we encounter a new node, increment count by 1
- if ultimate parent is not same, connect them, and reduce the count by 1
- for this, i make the union return a boolean
- non trivial e.g. below - 
  ```
  0 0 0
  1 0 0
  1 1 1
  ```
- query is 1,1
- first if left is checked, count reduces by 1, and now we have 1 big connected island
- now when down is checked, ultimate parent of both 1,1 and 2,1 come out to be same - union returns false and no changes are made
- another edge case in lintcode - same island can be present multiple times in the queries
- so, if queries are just `[[2,2],[2,2][]`, our logic would give 1,2, when actual answer should be 1,1. so, if a node is already visited, just add current number of archipelagos to result and continue

```java
import java.util.*;

public class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public static int[] numberOfIslandII(int n, int m, int [][]queries, int q) {

        int totalFlattenedNodes = n * m;
        DisjointSetUnion dsu = new DisjointSetUnion(totalFlattenedNodes);
        boolean[] visited = new boolean[totalFlattenedNodes];

        int numberOfArchipelagos = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < q; i++) {

            int x = queries[i][0];
            int y = queries[i][1];

            int currentNode = (x * m) + y;
            if (visited[currentNode]) {
                continue;
            }
            
            visited[currentNode] = true;
            numberOfArchipelagos += 1;
            
            for (int[] direction : directions) {
                
                int neighborX = x + direction[0];
                int neighborY = y + direction[1];

                if (neighborX > -1 && neighborX < n && neighborY > -1 && neighborY < m) {
                    
                    int neighborNode = (neighborX * m) + neighborY;
                    
                    if (visited[neighborNode]) {

                        boolean isDifferentComponents = dsu.union(currentNode, neighborNode);

                        if (isDifferentComponents) {
                            numberOfArchipelagos -= 1;
                        }
                    }
                }
            }

            result.add(numberOfArchipelagos);
        }

        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
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

        boolean union(int u, int v) {
            
            int parentU = findParent(u);
            int parentV = findParent(v);

            if (parentU == parentV) {
                return false;
            }

            if (rank[parentU] < rank[parentV]) {
                return union(v, u);
            } else {
                parent[parentV] = parentU;
                if (rank[parentU] == rank[parentV]) {
                    rank[parentU] += 1;
                }
                return true;
            }
        }
    }
}
```
