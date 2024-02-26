# Prim's Algorithm

- https://www.codingninjas.com/studio/problems/minimum-spanning-tree_631769
- start with any node and put it into priority queue
- a node in the min heap comprises of - node, weight of edge and parent
- if already visited, just continue the loop without iterating over neighbors
- note how we mark as visited only after removing from min heap
- iterate over neighbors and push into priority queue
- intuition - greedy - pick the edges with the minimum weight. no point picking edges where the destination node is already picked
- time complexity - O(E * logE) - in a graph like a star, all edges would be pushed in one pass, making heap size E

```java
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {

		List<List<Edge>> graph = constructGraph(edges, n);

		return primsAlgorithm(graph, n);
	}

	private static int primsAlgorithm(List<List<Edge>> graph, int n) {
		
		int startVertex = 0;

		boolean[] visited = new boolean[n];
		visited[startVertex] = true;

		PriorityQueue<Edge> minHeap = new PriorityQueue<>();
		for (Edge edge : graph.get(startVertex)) {
			minHeap.add(edge);
		}

		List<Edge> edgesPicked = new ArrayList<>();

		while (!minHeap.isEmpty()) {

			Edge edge = minHeap.poll();
			if (visited[edge.to]) continue;

			visited[edge.to] = true;
			edgesPicked.add(edge);

			for (Edge neighbor : graph.get(edge.to)) {
				if (!visited[neighbor.to]) {
					minHeap.add(neighbor);
				}
			}

			// System.out.println(minHeap);
		}

		int minSum = 0;
		for (Edge edge : edgesPicked) {
			minSum += edge.weight;
		}
		return minSum;
	}

	private static List<List<Edge>> constructGraph(ArrayList<ArrayList<Integer>> edges, int n) {
		
		List<List<Edge>> graph = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (ArrayList<Integer> edge : edges) {
			graph.get(edge.get(0)).add(new Edge(edge.get(0), edge.get(1), edge.get(2)));
			graph.get(edge.get(1)).add(new Edge(edge.get(1), edge.get(0), edge.get(2)));
		}

		return graph;
	}

	static class Edge implements Comparable<Edge> {
		
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge edge) {
			return weight - edge.weight;
		}

		@Override
		public String toString() {
			return "((" + from + ", " + to + "): " + weight + ")";
		}
	}
}
```
