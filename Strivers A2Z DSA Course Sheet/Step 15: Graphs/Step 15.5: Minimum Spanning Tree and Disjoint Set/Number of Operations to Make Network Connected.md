# Number of Operations to Make Network Connected

- https://leetcode.com/problems/number-of-operations-to-make-network-connected/
- if we have n components, we need n - 1 extra cables
- so, we need extra edges to be >= n - 1
- if possible, return n - 1
- else, return -1
- notice the use of getParent to find number of distinct components efficiently
- we can also make finding number of components a method exposed by dsu for convenience for clean code

```java
class Solution {

    public int makeConnected(int n, int[][] connections) {
        
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int excessEdges = 0;
        
        for (int[] connection : connections) {
            
            int parentU = dsu.findParent(connection[0]);
            int parentV = dsu.findParent(connection[1]);

            if (parentU == parentV) {
                excessEdges += 1;
            } else {
                dsu.union(parentU, parentV);
            }
        }
        
        int numberOfComponents = 0;

        for (int i = 0; i < n; i++) {
            if (dsu.getParent(i) == i) {
                numberOfComponents += 1;
            }
        }

        return (excessEdges >= numberOfComponents - 1) ? (numberOfComponents - 1) : -1;
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

        int getParent(int u) {
            return parent[u];
        }

        int findParent(int u) {
            if (parent[u] == u) {
                return u;
            }
            parent[u] = findParent(parent[u]);
            return parent[u];
        }

        void union(int u, int v) {
            
            int parentU = findParent(u);
            int parentV = findParent(v);

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
