# Topological Sorting

- https://www.codingninjas.com/studio/problems/topological-sorting_973003
- a linear ordering between u and v such that if there is an edge from u to v, u occurs before v
- so obviously, it is only possible for dags or directed acyclic graphs
- time complexity near about O(V + E)

### DFS

- we perform dfs and push the nodes onto a stack (the last node in the dfs gets pushed first into the stack)
- intuition - if we start at x, we know all nodes reachable from x will automatically be after x because of our algorithm
- now, the only possibility is that some node can reach x, all nodes reachable from x have been processed
- so, our best bet would be to place all remaining elements before x if possible
  - if x was reachable from them, then our order is correct
  - if x was not reachable from them, does not matter, we could have placed before or after, we placed before

```java
import java.util.*;

public class Solution {

    public static List<Integer> topologicalSort(int[][] edges, int e, int v) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        List<List<Integer>> adj = constructAdj(edges, e, v);
        boolean[] vis = new boolean[v];
        
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                vis[i] = true;
                dfs(adj, stack, vis, i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.removeLast());
        }
        return result;
    }

    private static void dfs(List<List<Integer>> adj, Deque<Integer> stack, boolean[] vis, int node) {
        
        for (int neighbor : adj.get(node)) {
            if (!vis[neighbor]) {
                vis[neighbor] = true;
                dfs(adj, stack, vis, neighbor);
            }
        }

        stack.addLast(node);
    }

    private static List<List<Integer>> constructAdj(int[][] edges, int e, int v) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        return adj;
    }
}
```

### BFS

- also called kahns algorithm
- insert all nodes with indegree as 0 into the queue
- as we remove nodes from the queue, reduce indegree of the neighbors by 1
- of the indegree of the neighbor becomes 0, add them to the queue
- tracking visited is not needed

```java
import java.util.*;

public class Solution {

    public static List<Integer> topologicalSort(int[][] edges, int e, int v) {

        int[] indegree = new int[v];

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            indegree[edge[1]] += 1;
            adj.get(edge[0]).add(edge[1]);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                queue.addLast(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {

            int node = queue.removeFirst();
            result.add(node);

            for (int neighbor : adj.get(node)) {
                indegree[neighbor] -= 1;
                if (indegree[neighbor] == 0) {
                    queue.addLast(neighbor);
                }
            }
        }

        return result;
    }
}
```
