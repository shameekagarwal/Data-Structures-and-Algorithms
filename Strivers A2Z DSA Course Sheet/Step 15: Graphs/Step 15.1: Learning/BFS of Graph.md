# BFS of Graph

- https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
- we use "levels" to denote how far a node is from the point we started the bfs
- we use a visited array
- we start with an initial node, and mark it as visited
- traverse the neighbors of the popped of node if it is not visited
- time complexity - the loop `!q.isEmpty()` runs for N, while the loop for neighbors runs for E times
- so, the total complexity - O(N + E)
- for undirected graph, the neighbors loop would run 2*E times

```java
class Solution {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        
        ArrayList<Integer> bfs = new ArrayList<Integer>();
        
        boolean[] visited = new boolean[adj.size()];
        visited[0] = true;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(0);
        
        while (!queue.isEmpty()) {
            
            int node = queue.removeFirst();
            bfs.add(node);
            
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.addLast(neighbor);
                }
            }
        }
        
        return bfs;
    }
}
```
