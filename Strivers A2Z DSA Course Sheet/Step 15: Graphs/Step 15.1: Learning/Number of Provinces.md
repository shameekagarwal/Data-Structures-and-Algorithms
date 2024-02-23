# Number of Provinces

- https://leetcode.com/problems/number-of-provinces/
- iterate through all nodes
- if a node is not visited, call bfs on it, and increment the number of connected components by 1

```java
class Solution {
    
    public int findCircleNum(int[][] isConnected) {
        
        boolean[] visited = new boolean[isConnected.length];
        
        int numberOfConnectedComponents = 0;
        
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                numberOfConnectedComponents += 1;
                bfs(i, isConnected, visited);
            }
        }
        
        return numberOfConnectedComponents;
    }

    private void bfs(int node, int[][] isConnected, boolean[] visited) {

        visited[node] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(node);

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();
            for (int i = 0; i < isConnected.length; i++) {
                if (i != current && !visited[i] && isConnected[current][i] == 1) {
                    visited[i] = true;
                    queue.addLast(i);
                }
            }
        }
    }
}
```
