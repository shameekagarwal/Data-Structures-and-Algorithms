# Evaluate Division

- https://leetcode.com/problems/evaluate-division/
- build a graph - a to b has weight a/b, b to a has weight b/a
- learning - we can find path between any two nodes in O(N) - this might be better than O(N^3) for doing it for all pairs of nodes

## My Solution

- time complexity is bad - `O(N^3)`
  - O(E) to build the graph from the equations
  - O(N) to find the distinct components numbers are in
  - O(N^3) to perform floyd warshall
  - O(Q) to perform queries - it only takes O(1) for queries now
    - just mark as -1 if the nodes have never been seen or not part of same component
    - mark as 1 if nodes are same
    - else use the populated floyd warshall matrix

```java
class Solution {

    public double[] calcEquation(List<List<String>> equations, 
        double[] values, List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < values.length; i++) {

            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);

            addEdge(graph, from, to, values[i]);
            addEdge(graph, to, from, 1 / values[i]);
        }

        Set<String> nodes = graph.keySet();
        Map<String, Integer> visited = new HashMap<>();
        int component = 1;

        for (String node : nodes) {
            dfs(visited, node, graph, component);
            component += 1;
        }

        int q = queries.size();
        double[] result = new double[q];

        floydWarshall(nodes, graph);
        
        for (int i = 0; i < q; i++) {

            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);

            if (!nodes.contains(from) || !nodes.contains(to)) {
                result[i] = -1.0;
            } else if (from.equals(to)) {
                result[i] = 1.0;
            } else if (!graph.get(from).containsKey(to)) {
                result[i] = -1.0;
            } else {
                result[i] = graph.get(from).get(to);
            }
        }

        return result;
    }

    private void floydWarshall(Set<String> nodes, Map<String, Map<String, Double>> graph) {

        for (String via : nodes) {

            for (String from : nodes) {

                for (String to : nodes) {

                    if (graph.get(from).containsKey(to)) continue;

                    if (graph.get(from).containsKey(via) && graph.get(via).containsKey(to)) {

                        double result = graph.get(from).get(via) * graph.get(via).get(to);
                        graph.get(from).put(to, result);
                        graph.get(to).put(from, 1 / result);
                    }
                }
            }
        }
    }

    private void dfs(Map<String, Integer> visited, String src, 
        Map<String, Map<String, Double>> graph, int component) {

        if (visited.containsKey(src)) return;

        visited.put(src, component);

        for (String neighbor : graph.get(src).keySet()) {
            dfs(visited, neighbor, graph, component);
        }
    }

    private void addEdge(Map<String, Map<String, Double>> graph, String from, String to, double value) {

        if (!graph.containsKey(from)) {
            graph.put(from, new HashMap<>());
        }

        graph.get(from).put(to, value);
    }
}
```

## Suggested Solution

- `O(Q*N)`
- for each query, spend O(N) time to compute the distance 
- we can use dfs for this

```java
class Solution {

    public double[] calcEquation(List<List<String>> equations, 
        double[] values, List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = constructGraph(equations, values);

        int q = queries.size();
        double[] result = new double[q];

        for (int i = 0; i < q; i++) {

            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);

            if (!graph.containsKey(from) || !graph.containsKey(to)) {
                result[i] = -1;
            } else if (from.equals(to)) {
                result[i] = 1;
            } else {
                Set<String> visited = new HashSet<>();
                result[i] = dfs(to, from, visited, graph);
            }
        }

        return result;
    }

    private double dfs(String to, String src, Set<String> visited, Map<String, Map<String, Double>> graph) {

        if (visited.contains(src)) return -1;

        visited.add(src);

        for (String neighbor : graph.get(src).keySet()) {

            if (neighbor.equals(to)) {
                return graph.get(src).get(neighbor);
            } else {
                double result = dfs(to, neighbor, visited, graph);
                if (result != -1) {
                    return result * graph.get(src).get(neighbor);
                }
            }
        }

        return -1;
    }

    private Map<String, Map<String, Double>> constructGraph(List<List<String>> equations, double[] values) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < values.length; i++) {

            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);

            addEdge(graph, from, to, values[i]);
            addEdge(graph, to, from, 1 / values[i]);
        }

        return graph;
    }

    private void addEdge(Map<String, Map<String, Double>> graph, String from, String to, double weight) {

        if (!graph.containsKey(from)) {
            graph.put(from, new HashMap<>());
        }

        graph.get(from).put(to, weight);
    }
}
```
