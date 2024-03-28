# Kosaraju's Algorithm

- https://www.naukri.com/code360/problems/count-strongly-connected-components-kosaraju-s-algorithm_1171151
- scc - strongly connected components
- two variants
  - finding number of sccs
  - print the sccs
- both are possible to solve using current approach with slight modifications, solving for first variant here
- note - "sccs are only valid for directed graphs"
- sccs - any pair in the graph is reachable from one another
- so, if we "reverse the edges" - nodes of the same scc will still be reachable from one another
- now, store the topological sort i.e. if y is reachable from x, x will come before y
- now, when we perform dfs from reversed graph - x would be reachable from y (because basically edges have been reversed)
  - imagine x -> y was x -> a -> b -> c -> y
  - on reversing, we can do y -> c -> b -> a -> x
- however, y will only be reachable from x again if they are part of the same scc

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

	public static int stronglyConnectedComponents(int v, ArrayList<ArrayList<Integer>> edges) {

		List<Integer> result = new ArrayList<>();
		List<Integer> reversedResult = new ArrayList<>();
		List<List<Integer>> graph = new ArrayList<>();
		List<List<Integer>> reversedGraph = new ArrayList<>();
		boolean[] visited = new boolean[v];

		for (int i = 0; i < v; i++) {
			graph.add(new ArrayList<>());
			reversedGraph.add(new ArrayList<>());
		}
		
		for (List<Integer> edge : edges) {
			graph.get(edge.get(0)).add(edge.get(1));
			reversedGraph.get(edge.get(1)).add(edge.get(0));
		}

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i, result, graph, visited);
			}
		}

		Collections.reverse(result);
		Arrays.fill(visited, false);

		int sccs = 0;

		for (int i = 0; i < v; i++) {
			if (!visited[result.get(i)]) {
				visited[result.get(i)] = true;
				dfs(result.get(i), reversedResult, reversedGraph, visited);
				sccs += 1;
			}
		}

		return sccs;
	}

	private static void dfs(int src, List<Integer> result, List<List<Integer>> graph, boolean[] visited) {

		for (int neighbor : graph.get(src)) {
			if (!visited[neighbor]) {
				visited[neighbor] = true;
				dfs(neighbor, result, graph, visited);
			}
		}

		result.add(src);
	}
}
```
