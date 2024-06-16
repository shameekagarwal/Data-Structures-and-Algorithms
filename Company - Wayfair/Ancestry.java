import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ancestry {

  public static void main(String[] args) {

    Ancestry ancestry = new Ancestry();

    int[][] edges = new int[][] { new int[] { 1, 3 }, new int[] { 2, 3 }, new int[] { 3, 6 }, new int[] { 5, 6 },
        new int[] { 5, 7 }, new int[] { 4, 5 }, new int[] { 4, 8 }, new int[] { 8, 9 }, new int[] { 10, 2 } };

    int[] result = ancestry.run(10, edges);

    System.out.println(Arrays.toString(result));
  }

  private int[] run(int n, int[][] edges) {

    int[] indegree = new int[n + 1];

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      indegree[edge[1]] += 1;
    }

    boolean[] visited = new boolean[n + 1];
    int[] result = new int[n + 1];

    for (int i = 1; i <= n; i++) {

      if (indegree[i] == 0) {
        dfs(i, i, graph, result, visited);
        result[i] = -1;
      }
    }

    return Arrays.copyOfRange(result, 1, n + 1);
  }

  private void dfs(int node, int ancestor, List<List<Integer>> graph, int[] result, boolean[] visited) {

    if (visited[node])
      return;

    visited[node] = true;
    result[node] = ancestor;

    for (int neighbor : graph.get(node)) {
      dfs(neighbor, ancestor, graph, result, visited);
    }
  }
}