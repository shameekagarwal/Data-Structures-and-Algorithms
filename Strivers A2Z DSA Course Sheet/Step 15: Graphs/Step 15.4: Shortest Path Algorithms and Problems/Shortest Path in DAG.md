# Shortest Path in DAG

- https://www.codingninjas.com/studio/problems/shortest-path-in-dag_8381897
- directed acyclic graph, and edges have weights
- perform topological sort using dfs
- the stack should be populated using the reverse of the topological sort
- make a distance array initialized with infinity
- make the source node's value as 0 (distance from node to itself is 0)
- take the nodes out of the topological sort one by one
- update the distance array one by one
- edge case - node popped from stack itself has distance as infinity - do not process its neighbors, just continue to pop the next element from stack
  ```java
  if (distance[node] == -1) {
      continue;
  }
  ```
- time complexity - O(V + E)
- why does this work?
- topological sort ensures that before vertex x is processed, all vertices that can reach it proceed it
- this way by the time vertex x is processed, we already know the shortest distance to x
- so why do we even need dijkstra?! - because this method i.e. topological sort will not work for graphs with cycles

```java
import java.util.*;

public class Solution {

    public static int[] shortestPathInDAG(int n, int m, int[][] edges) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                visited[node] = true;
                dfs(visited, graph, stack, node);
            }
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        distance[0] = 0;

        while (!stack.isEmpty()) {

            int node = stack.removeLast();
            if (distance[node] == -1) {
                continue;
            }

            for (int[] neighbor : graph.get(node)) {
                if (distance[neighbor[0]] == -1 || distance[neighbor[0]] > distance[node] + neighbor[1]) {
                    distance[neighbor[0]] = distance[node] + neighbor[1];
                }
            }
        }

        return distance;
    }

    private static void dfs(boolean[] visited, List<List<int[]>> graph, Deque<Integer> stack, int node) {

        for (int[] neighbor : graph.get(node)) {
            if (!visited[neighbor[0]]) {
                visited[neighbor[0]] = true;
                dfs(visited, graph, stack, neighbor[0]);
            }
        }

        stack.addLast(node);
    }
}
```
