# Single Source Shortest Path

- https://www.codingninjas.com/studio/problems/single-source-shortest-path_8416371
- "undirected graphs" is key
- simple bfs
- there are two approaches 
  - mark visited / distance after when enqueuing
  - mark visited / distance when dequeuing
- i always follow the first approach
- disadvantage of marking it when dequeuing - it might happen that a node has already been added to the queue, but not been dequeued, hence not yet marked visited
- this way, another node with this node as the neighbor will add it to the queue again - so now, this node is in the queue multiple times

```java
import java.util.*;

public class Solution {

    public static int[] shortestPath(int n, int[][] edges, int src) {
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{src, 0});
        distance[src] = 0;

        while (!queue.isEmpty()) {
            
            int[] pair = queue.removeFirst();

            // System.out.println("after: " + Arrays.toString(pair) + ", distance: " + Arrays.toString(distance));

            for (int neighbor : graph.get(pair[0])) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = pair[1] + 1;
                    queue.addLast(new int[]{neighbor, pair[1] + 1});
                }
            }
        }

        return distance;
    }
}
```
