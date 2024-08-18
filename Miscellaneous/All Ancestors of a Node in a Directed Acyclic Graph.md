# All Ancestors of a Node in a Directed Acyclic Graph

- https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/

## Approach 1

- reverse the edges
- now for every node, we do a dfs
- this way, we end up visiting all the nodes that this node can reach - but not in a sorted order
- so finally, we sort this path before adding it to the result
- time complexity - O(N^2 * logN)

```java
class Solution {

    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[1]).add(edge[0]);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            boolean[] visited = new boolean[n];
            List<Integer> reachable = new ArrayList<>();
            
            dfs(i, visited, graph, reachable);
            
            reachable.remove(reachable.size() - 1);
            Collections.sort(reachable);
            
            result.add(reachable);
        }

        return result;
    }

    private void dfs(int node, boolean[] visited, List<List<Integer>> graph, List<Integer> reachable) {

        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            dfs(neighbor, visited, graph, reachable);
        }

        reachable.add(node);
    }
}
```

## Approach 2

- do not reverse the edges
- instead, for nodes starting from 0 to n - 1, try visiting all nodes
- so basically say 0 can reach nodes x, y, z. then all x, y z will have 0 as its first element
- sat 1 can reach nodes x, y, p. x and y will have 1 as their second element, but p will have it as its first element
- and so on...
- advantage from approach 1- we get rid of the sorting step
- my thought around why visited might be needed - what it 1 can reach 2 and 3, and both 2 and 3 can reach 4 - we do not want to add 1 twice for 4?
  ```
  1 - 2   
   \   \
    3 - 4
  ```
- also our current algorithm adds 1 to 1 - so, at the end of every iteration, fix that i.e. remove last element

```java
class Solution {

    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {

            boolean[] visited = new boolean[n];
            dfs(i, i, visited, graph, result);
            result.get(i).remove(result.get(i).size() - 1);
        }

        return result;
    }

    private void dfs(int node, int ancestor, boolean[] visited, List<List<Integer>> graph, List<List<Integer>> result) {

        if (visited[node]) return;

        visited[node] = true;

        result.get(node).add(ancestor);

        for (int neighbor : graph.get(node)) {
            dfs(neighbor, ancestor, visited, graph, result);
        }
    }
}
```
