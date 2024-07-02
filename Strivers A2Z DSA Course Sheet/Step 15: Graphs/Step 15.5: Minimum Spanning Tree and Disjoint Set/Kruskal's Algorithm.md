# Kruskal's Algorithm

- https://www.codingninjas.com/studio/problems/minimum-spanning-tree_631769
- kruskal's algorithm can be used to find minimum spanning tree
- step 1 - sort edges by weight in increasing order
- step 2 - pick the edges one by one, and call union on disjoint set on the endpoints of the edge
- only add an edge if the "ultimate parents" of the two endpoints we try to union are different
- time complexity - O(M*logM)

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public static int kruskalMST(int n, int [][]edges) {

        List<Edge> edgeList = new ArrayList<>();

        for (int[] edge : edges) {
            edgeList.add(new Edge(edge[0], edge[1], edge[2]));
        }

        Collections.sort(edgeList);

        DisjointSetUnion dsu = new DisjointSetUnion(n);
        
        int minWeight = 0;

        for (Edge edge : edgeList) {
            int parentFrom = dsu.findParent(edge.from);
            int parentTo = dsu.findParent(edge.to);
            if (parentFrom != parentTo) {
                dsu.union(edge.from, edge.to);
                minWeight += edge.weight;
            }
        }

        return minWeight;
    }

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge(from=" + from + ", to=" + to + ",  weight=" + weight + ")";
        }

        @Override
        public int compareTo(Edge edge) {
            return weight - edge.weight;
        }
    }

    static class DisjointSetUnion {

        private int[] parent;
        private int[] rank;

        DisjointSetUnion(int n) {
            
            parent = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                parent[i] = i;
            }

            rank = new int[n + 1];
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
