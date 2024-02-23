# DFS of Graph

- https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
- travel the entire depth first from a node
- notice how the implementation is just like [bfs](./BFS%20of%20Graph.md) i.e. if not visited, mark visited but then here we call dfs while there, we would add too the queue. but the structure is the same
- time complexity - dfs is called N times and the neighbors loop runs E times, so O(N + E)
- again for undirected graph, the neighbors loop would run 2*E times

```java
class Solution {

    private boolean[] visited;
    private ArrayList<Integer> result;
    private ArrayList<ArrayList<Integer>> adj;

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        
        result = new ArrayList<Integer>();
        visited = new boolean[adj.size()];
        this.adj = adj;
        visited[0] = true;
        dfs(0);
        
        return result;
    }
    
    private void dfs(int node) {
        result.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(neighbor);
            }
        }
    }
}
```
