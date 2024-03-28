# Detect Cycle in an Undirected Graph

- https://www.codingninjas.com/studio/problems/detect-cycle-in-an-undirected-graph-_758967
- if we can visit an already visited node - there is a cycle
- we have to keep track of parent as well since we can travel back to parent from a node - parent will be marked visited already, but that does not mean there is a cycle
- we can use -1 as parent when starting the traversal from a node
- "undirected graph" is the important keyword here
- a graph can have multiple components, out of which one (or some) have cycles, so we need to combine our approach with [Number of Provinces](../Step%2015.1:%20Learning/Number%20of%20Provinces.md)
- time complexity - detect cycle for every component - O(N) + O(2*E), add an extra O(N) for multiple components

### Using BFS

```java
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {
    
    static class Graph {

        private int V;
        private List<List<Integer>> adj;
        private boolean[] vis;

        boolean detectCycle(int V, List<List<Integer>> adj) {
            
            this.V = V;
            this.adj = adj;
            this.vis = new boolean[V];

            for (int i = 0; i < V; i++) {
                if (!vis[i]) {
                    if (hasCycle(i)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean hasCycle(int node) {

            vis[node] = true;
            Deque<int[]> queue = new ArrayDeque<>();
            queue.addLast(new int[]{node, -1});

            while (!queue.isEmpty()) {

                int current = queue.peekFirst()[0];
                int parent = queue.peekFirst()[1];
                queue.removeFirst();

                for (int neighbor : adj.get(current)) {
                    if (neighbor == parent) continue;
                    if (vis[neighbor]) return true;
                    vis[neighbor] = true;
                    queue.addLast(new int[]{neighbor, current});
                }
            }

            return false;
        }
    }
}
```

### Using DFS

```java
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {
    
    static class Graph {

        private int V;
        private List<List<Integer>> adj;
        private boolean[] vis;

        boolean detectCycle(int V, List<List<Integer>> adj) {
            
            this.V = V;
            this.adj = adj;
            this.vis = new boolean[V];

            for (int i = 0; i < V; i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    if (hasCycle(i, -1)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean hasCycle(int node, int parent) {

            for (int neighbor : adj.get(node)) {
                
                if (neighbor == parent) continue;
                if (vis[neighbor]) return true;
                
                vis[neighbor] = true;
                if (hasCycle(neighbor, node)) return true;
            }

            return false;
        }
    }
}
```
