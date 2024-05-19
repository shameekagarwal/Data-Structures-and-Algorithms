# Number of Connected Components in an Undirected Graph

- https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
- time complexity important for interview - o(v + e)

```java
class Solution {

    public int countComponents(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int components = 0;
        
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {
                components += 1;
                dfs(i, graph, visited);
            }
        }

        return components;
    }

    private void dfs(int src, List<List<Integer>> graph, boolean[] visited) {

        if (visited[src]) return;

        visited[src] = true;

        for (int neighbor : graph.get(src)) {
            dfs(neighbor, graph, visited);
        }
    }
}
```
