# Cycle Detection in Directed Graph (BFS)

- https://www.codingninjas.com/studio/problems/detect-cycle-in-a-directed-graph_1062626
- [this method](../Step%2015.2:%20Problems%20on%20BFS%20DFS/Cycle%20Detection%20in%20Directed%20Graph%20(DFS).md) used dfs, current one uses bfs
- we use [kahns algorithm](./Topological%20Sorting.md)
- if topological sort does not have length n, there is a cycle

```java
import java.util.*;

public class Solution {

  public static boolean detectCycleInDirectedGraph(int n, ArrayList<ArrayList<Integer>> edges) {

    List<List<Integer>> adj = new ArrayList<>();
    
    for (int node = 0; node < n; node++) {
      adj.add(new ArrayList<>());
    }
    
    for (ArrayList<Integer> edge : edges) {
      adj.get(edge.get(0) - 1).add(edge.get(1) - 1);
    }

    int[] indegree = new int[n];
    for (int node = 0; node < n; node++) {
      for (int neighbor : adj.get(node)) {
        indegree[neighbor] += 1;
      }
    }

    Deque<Integer> queue = new ArrayDeque<>();
    for (int node = 0; node < n; node++) {
      if (indegree[node] == 0) {
        queue.addLast(node);
      }
    }

    while (!queue.isEmpty()) {
      
      int node = queue.removeFirst();

      for (int neighbor : adj.get(node)) {
        indegree[neighbor] -= 1;
        if (indegree[neighbor] == 0) {
          queue.addLast(neighbor);
        }
      }

    }

    for (int node = 0; node < n; node++) {
      if (indegree[node] != 0) {
        return true;
      }
    }

    return false;
  }
}
```
