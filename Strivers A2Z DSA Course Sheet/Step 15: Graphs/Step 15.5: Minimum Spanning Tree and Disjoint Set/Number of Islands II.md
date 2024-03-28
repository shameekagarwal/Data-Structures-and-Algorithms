# Number of Islands II

- https://www.codingninjas.com/studio/problems/largest-island_840701
- my understanding - main challenge is maintaining a "count on the fly" of the number of components in a dsu
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
- so, if queries are just `[[2,2],[2,2]]`, our logic would give 1,2, when actual answer should be 1,1. so, if a node is already visited, just add current number of archipelagos to result and continue
- dsu with put functionality - this is a variation of the typical dsu problem that i have seen, that can be solved in this way

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
        
        int size = n * m;
        DSU dsu = new DSU(size);

        int[] result = new int[q];

        int groups = 0;

        for (int i = 0; i < q; i++) {
            
            int flattenedCoordinate = (queries[i][0] * m) + queries[i][1];
            dsu.put(flattenedCoordinate);

            groups += 1;

            for (int[] direction : directions) {
                
                int newX = queries[i][0] + direction[0];
                int newY = queries[i][1] + direction[1];

                if (newX > -1 && newX < n && newY > -1 && newY < m) {
                    int flattenedNeigboringCoordinate = (newX * m) + newY;
                    if (dsu.union(flattenedNeigboringCoordinate, flattenedCoordinate)) {
                        groups -= 1;
                    }
                }
            }

            result[i] = groups;
        }

        return result;
    }

    static class DSU {

        int[] parent;
        int[] rank;

        DSU(int size) {
            
            parent = new int[size];
            rank = new int[size];
            
            Arrays.fill(parent, -1);
            Arrays.fill(rank, -1);
        }

        int findParent(int x) {

            if (parent[x] == x) {
                return x;
            }

            parent[x] = findParent(parent[x]);
            return parent[x];
        }

        void put(int x) {
            parent[x] = x;
            rank[x] = 1;
        }

        boolean union(int x, int y) {

            if (parent[x] == -1 || parent[y] == -1) return false;
            
            int parentX = findParent(x);
            int parentY = findParent(y);

            if (parentX == parentY) {
                return false;
            }

            // System.out.println("union: " + x + ", " + y);

            if (rank[parentX] < rank[parentY]) {
                return union(y, x);
            } else {
                parent[parentY] = parentX;
                if (rank[parentX] == rank[parentY]) {
                    rank[parentX] += 1;
                }
            }

            return true;
        }
    }
}
```
