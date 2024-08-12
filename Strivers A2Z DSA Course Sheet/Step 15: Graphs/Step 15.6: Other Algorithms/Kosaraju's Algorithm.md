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
import java.util.*;

public class Solution {

	public static int stronglyConnectedComponents(int v, ArrayList<ArrayList<Integer>> edges) {

		List<List<Integer>> graph = createGraph(v, edges);
		List<Integer> sort = topologicalSort(v, graph);
		List<List<Integer>> reversedGraph = reverseGraph(v, graph);

		return findSccs(v, sort, reversedGraph);
	}

	private static int findSccs(int v, List<Integer> sort, List<List<Integer>> graph) {

		boolean[] visited = new boolean[v];
		int sccs = 0;

		for (int node : sort) {
			
			if (!visited[node]) {

				dfs(node, visited, graph);
				sccs += 1;
			}
		}

		return sccs;
	}

	private static List<Integer> topologicalSort(int v, List<List<Integer>> graph) {

		List<Integer> sort = new ArrayList<>();
		boolean[] visited = new boolean[v];

		for (int i = 0; i < v; i++) {

			dfs(i, sort, visited, graph);
		}

		Collections.reverse(sort);

		return sort;
	}

	
	private static void dfs(int node, boolean[] visited, List<List<Integer>> graph) {

		if (visited[node]) return;
		visited[node] = true;

		for (int neighbor : graph.get(node)) {
			dfs(neighbor, visited, graph);
		}
	}

	private static void dfs(int node, List<Integer> sort, boolean[] visited, List<List<Integer>> graph) {

		if (visited[node]) return;
		visited[node] = true;

		for (int neighbor : graph.get(node)) {
			dfs(neighbor, sort, visited, graph);
		}

		sort.add(node);
	}

	private static List<List<Integer>> createGraph(int v, ArrayList<ArrayList<Integer>> edges) {

		List<List<Integer>> graph = new ArrayList<>();

		for (int i = 0; i < v; i++) {
			graph.add(new ArrayList<>());
		}

		for (ArrayList<Integer> edge : edges) {
			graph.get(edge.get(0)).add(edge.get(1));
		}

		return graph;
	}
	
	private static List<List<Integer>> reverseGraph(int v, List<List<Integer>> graph) {

		List<List<Integer>> reversedGraph = new ArrayList<>();

		for (int i = 0; i < v; i++) {
			reversedGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < v; i++) {

			for (int neighbor : graph.get(i)) {
				reversedGraph.get(neighbor).add(i);
			}
		}

		return reversedGraph;
	}
}
```
