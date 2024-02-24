# Find Eventual Safe States - DFS

- https://leetcode.com/problems/find-eventual-safe-states/
- safe node - ends at terminal node
- terminal node - out degree is 0
- two observations - 
  - any node part of a cycle cannot be a safe node
  - any node having a path leading to a cycle cannot be a safe node
- using [this](../Step%2015.2:%20Problems%20on%20BFS%20DFS/Cycle%20Detection%20in%20Directed%20Graph%20(DFS).md) almost as is
- there, we were preemptively returning once we knew there is a cycle
- here, we continue the algorithm
- also, all nodes which end at an unsafe node - (i.e. visited and path visited) are unsafe
- "all nodes having path visited as false are safe"
- note - usually, i write implementations where i mark visited first then make the recursive call. doing it inside the recursive call this time. a little bit duplication is removed this way

```java
class Solution {

    private int[][] graph;
    private int n;
    private boolean[] visited;
    private boolean[] pathVisited;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        this.graph = graph;
        n = graph.length;

        visited = new boolean[n];
        pathVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                hasCycle(i);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!pathVisited[i]) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    private boolean hasCycle(int node) {

        if (visited[node] && pathVisited[node]) {
            return true;
        } else if (visited[node]) {
            return false;
        }

        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbor : graph[node]) {
            if (hasCycle(neighbor)) {
                return true;
            }
        }

        pathVisited[node] = false;

        return false;
    }
}
```
