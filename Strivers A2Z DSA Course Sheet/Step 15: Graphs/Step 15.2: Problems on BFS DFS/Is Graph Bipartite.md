# Is Graph Bipartite

- https://leetcode.com/problems/is-graph-bipartite/
- 2 colors such that no two adjacent nodes have the same color
- observation - graph with odd length cycle cannot be bipartite
- i don't think observation is used
- we just try to color, and if color of adjacent node has same color, then return false preemptively
- else, finally return true

```java
class Solution {

    public boolean isBipartite(int[][] graph) {

        int[] color = new int[graph.length];
        
        // for disconnected components
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                if (!isBipartite(color, graph, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isBipartite(int[] color, int[][] graph, int V) {
        
        for (int neighbor : graph[V]) {
                
            // colored using same color
            if (color[neighbor] == color[V]) {
                return false;
            } else if (color[neighbor] != 0) {
                // already colored
                continue;
            } else {
                // color using opposite color
                color[neighbor] = 3 - color[V];
                // if not bipartite - fail fast
                if (!isBipartite(color, graph, neighbor)) {
                    return false;
                }
            }
        }

        return true;
    }
}
```
