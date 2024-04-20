# Minimum Cost Walk in Weighted Graph

- https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/
- about ands - x & x = x
- the more numbers we try to and, the higher are our chances of reducing cost
- and we are already given we can re travel on an edge
- and it is an undirected graph
- so, if nodes in the same component, cost = and of all numbers
- if nodes are not part of the same component, then -1
- step 1 - for all nodes, find the component it belongs to
- step 2 - build a map, where key is component, value is and of all edges inside it
- step 3 - for all queries, if nodes are in same component, print the value stored in map above, else print -1

```java
class Solution {

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int[] colors = new int[n];
        int color = 1;
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                bfs(colors, color, i, graph);
                color += 1;
            }
        }
        
        Map<Integer, Integer> cost = new HashMap<>();
        
        for (int[] edge : edges) {

            int component = colors[edge[0]];

            if (!cost.containsKey(component)) {
                cost.put(component, edge[2]);
            } else {
                cost.put(component, cost.get(component) & edge[2]);
            }
        }
                
        int q = query.length;
        int[] result = new int[q];
                
        for (int i = 0; i < q; i++) {
            
            int from = query[i][0];
            int to = query[i][1];
            
            if (colors[from] != colors[to]) {
                result[i] = -1;
            } else {
                result[i] = cost.get(colors[from]);
            }
        }
        
        return result;
    }
    
    void bfs(int[] colors, int color, int src, List<List<Integer>> graph) {
        
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(src);
        colors[src] = color;
        
        while (!queue.isEmpty()) {
            
            int node = queue.removeFirst();

            for (int neighbor : graph.get(node)) {
                if (colors[neighbor] == 0) {
                    queue.addLast(neighbor);
                    colors[neighbor] = color;
                }
            }
        }
    }
}
```
