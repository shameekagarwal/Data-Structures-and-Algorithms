# Longest Increasing Path in a Matrix

- https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
- realize that the graph is a dag
  - strictly increasing, so cycle not possible
  - directed because if a has an edge to b, b cannot have an edge to a
- also, it is unweighted
- step 1 - so, i first form a graph for this by flattening the coordinates
- how to calculate edges - traverse in all 4 directions, and ensure the new cell is valid and has greater value than source
- step 2 - perform a topological sort
- this is to help ensure that for e.g. a 9's result will be calculated before a neighboring for e.g. 7
- step 3 - start finding the longest path - using dfs
- if visited, return the longest path from that node
- else, calculate the longest path as max(neighbor's longest path + 1)

```java
class Solution {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    };

    public int longestIncreasingPath(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int N = m * n;

        List<List<Integer>> graph = constructGraph(matrix, m, n, N);
        List<Integer> topology = topologicalSort(graph, N);
        int[] pathLengths = getPathLengths(N, graph);

        int max = 0;
        for (int pathLength : pathLengths) {
            max = Math.max(max, pathLength);
        }

        return max;
    }

    private int[] getPathLengths(int n, List<List<Integer>> graph) {

        int[] pathLengths = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dfs(i, visited, pathLengths, graph);
        }

        return pathLengths;
    }

    private int dfs(int src, boolean[] visited, int[] pathLengths, List<List<Integer>> graph) {

        if (visited[src]) {
            return pathLengths[src];
        }

        pathLengths[src] = 1;

        for (int neighbor : graph.get(src)) {
            pathLengths[src] = Math.max(pathLengths[src], dfs(neighbor, visited, pathLengths, graph) + 1);
        }

        visited[src] = true;
        return pathLengths[src];
    }

    private List<Integer> topologicalSort(List<List<Integer>> graph, int n) {

        boolean[] visited = new boolean[n];
        List<Integer> topology = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            dfs(i, visited, graph, topology);
        }

        Collections.reverse(topology);

        return topology;
    }

    private void dfs(int src, boolean[] visited, List<List<Integer>> graph, List<Integer> topology) {

        if (visited[src]) return;

        visited[src] = true;

        for (int neighbor : graph.get(src)) {
            dfs(neighbor, visited, graph, topology);
        }

        topology.add(src);
    }

    private List<List<Integer>> constructGraph(int[][] matrix, int m, int n, int N) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int cell = getCell(i, j, n);

                for (int[] direction : directions) {

                    int x = i + direction[0];
                    int y = j + direction[1];

                    if (x > -1 && x < m && y > -1 && y < n) {

                        if (matrix[x][y] > matrix[i][j]) {

                            int neighbor = getCell(x, y, n);
                            graph.get(cell).add(neighbor);
                        }
                    }
                }
            }
        }

        return graph;
    }

    private int getCell(int i, int j, int cols) {
        return (i * cols) + j;
    }
}
```
