# Find Eventual Safe States - BFS

- https://leetcode.com/problems/find-eventual-safe-states/
- safe node - ends at terminal node
- terminal node - out degree is 0
- all terminal nodes are safe nodes
- terminal nodes = nodes which have outdegree as 0
- next - all nodes having all paths ending at one of these safe nodes are a safe node
- so, we first reverse all edges
- this means all nodes with indegree 0 are used (aka terminal nodes)
- next, all nodes that had paths to only these terminal nodes will be used
- so, if we simply use kahn after reversing the edges
- "all nodes with indegree 0" will be our answer

```java
class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int numberOfNodes = graph.length;

        // construct reversed edges
        List<List<Integer>> reversedEdges = new ArrayList<>();

        for (int node = 0; node < numberOfNodes; node++) {
            reversedEdges.add(new ArrayList<>());
        }

        for (int node = 0; node < numberOfNodes; node++) {
            for (int neighbor : graph[node]) {
                reversedEdges.get(neighbor).add(node);
            }
        }

        // construct indegree
        int[] indegree = new int[numberOfNodes];
        for (int node = 0; node < numberOfNodes; node++) {
            for (int neighbor : reversedEdges.get(node)) {
                indegree[neighbor] += 1;
            }
        }

        // enqueue nodes with indegree 0
        Deque<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < numberOfNodes; node++) {
            if (indegree[node] == 0) {
                queue.addLast(node);
            }
        }

        // bfs / kahn's algorithm
        while (!queue.isEmpty()) {
            
            int node = queue.removeFirst();

            for (int neighbor : reversedEdges.get(node)) {
                indegree[neighbor] -= 1;
                if (indegree[neighbor] == 0) {
                    queue.addLast(neighbor);
                }
            }
        }

        // safe nodes = nodes with zero indegree
        List<Integer> safeNodes = new ArrayList<>();
        for (int node = 0; node < numberOfNodes; node++) {
            if (indegree[node] == 0) {
                safeNodes.add(node);
            }
        }
        return safeNodes;
    }
}
```
