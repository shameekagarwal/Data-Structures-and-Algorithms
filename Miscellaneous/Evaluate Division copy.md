# Evaluate Division

- https://leetcode.com/problems/evaluate-division/

```java
class Solution {

    public double[] calcEquation(List<List<String>> equations, 
        double[] values, 
        List<List<String>> queries) {

        Map<String, List<Edge>> graph = constructGraph(equations, values);

        double[] result = new double[queries.size()];

        for (int i = 0; i < result.length; i++) {

            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);

            if (!graph.containsKey(a) || !graph.containsKey(b)) {
                result[i] = -1;
                continue;
            }

            if (a.equals(b)) {
                result[i] = 1;
                continue;
            }

            Set<String> visited = new HashSet<>();

            result[i] = dfs(a, b, graph, visited);
        }

        return result;
    }

    private double dfs(String node, String target, Map<String, List<Edge>> graph, Set<String> visited) {

        if (visited.contains(node)) return -1;

        visited.add(node);

        if (node.equals(target)) return 1.0;

        double result = -1;

        for (Edge edge : graph.get(node)) {
            
            double calculated = dfs(edge.node, target, graph, visited);

            if (calculated != -1) {
                result = calculated * edge.value;
                break;
            }
        }

        return result;
    }

    private Map<String, List<Edge>> constructGraph(List<List<String>> equations, double[] values) {

        Map<String, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < values.length; i++) {

            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList<>());
            }

            graph.get(a).add(new Edge(b, values[i]));

            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList<>());
            }

            graph.get(b).add(new Edge(a, 1 / values[i]));
        }

        return graph;
    }

    static class Edge {

        String node;
        double value;

        Edge(String node, double value) {
            this.node = node;
            this.value = value;
        }
    }
}
```
