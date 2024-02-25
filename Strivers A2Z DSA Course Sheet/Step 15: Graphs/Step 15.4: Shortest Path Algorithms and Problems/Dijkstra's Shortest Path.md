# Dijkstra's Shortest Path

- https://www.codingninjas.com/studio/problems/dijkstra's-shortest-path_985358
- use min heap
- initialize distance array with infinity and 0 for source
- add (source node, 0) to min heap
- keep popping from priority queue till it is not empty
- if distance can be reduced
  - reduce distance in the distance array (assume initial distance is infinite)
  - add to min heap
- note - this question is undirected graph, so why use dijkstra?
  - because edges have weights - otherwise i think we could have used [this](./Single%20Source%20Shortest%20Path.md)
  - because there can be loops - otherwise i think we could have used [this](./Shortest%20Path%20in%20DAG.md)
- if question asks to print parent - we try to remember "where we came from"
- dijkstra does not work with negative weight - imagine a graph like this - 
  ```
  1 <----- -2 -----> 0
  ```
- we end up in an infinite loop - 1 can reach 0 using -2, then back to 1 using -4, then back to 0 using -6 and so on
- time complexity - O(E*logV)
- note - we can use a set (not unordered set, but something like tree set i believe) instead of a priority queue
- advantage - when we use a priority queue, we can have the same node multiple times in it - priority queue by default does support deleting of specific nodes. however, set can perform erase operations
- so, when we were updating the distance - if we see `distance[neighbor] != infinity` - we can be sure that it "might exist" in the set - so we erase it from the set
- the time complexity if using a set instead of a priority queue may or may not improve, depending on the graph
- if we use a queue instead of a priority queue - "the question would still be solved" - but, we would end up exploring a lot more paths, thus increasing the time complexity to be quadratic

```java
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

public class Solution {

    public static List<Integer> dijkstra(int[][] edgeList, int vertices, int edges, int source) {
        
        // construct adjacency matrix
        List<List<Edge>> graph = new ArrayList<>();
        
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edgeList) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }

        // perform disktras algorithm
        PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>();
        List<Integer> distance = new ArrayList<>(Collections.nCopies(vertices, -1));
        distance.set(source, 0);
        minHeap.add(new MinHeapNode(source, 0));
        
        while (!minHeap.isEmpty()) {
            
            MinHeapNode minHeapNode = minHeap.poll();
            
            for (Edge edge : graph.get(minHeapNode.node)) {
                if (distance.get(edge.neighbor) == -1 || distance.get(edge.neighbor) > distance.get(minHeapNode.node) + edge.weight) {
                    distance.set(edge.neighbor, distance.get(minHeapNode.node) + edge.weight);
                    minHeap.add(new MinHeapNode(edge.neighbor, distance.get(edge.neighbor)));
                }
            }
        }

        return distance;
    }

    static class Edge {

        int neighbor;
        int weight;

        Edge(int neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
        
        @Override
        public String toString() {
            return "(" + neighbor + ", " + weight + ")";
        }
    }

    static class MinHeapNode implements Comparable<MinHeapNode> {
        
        int node;
        int distance;

        MinHeapNode(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(MinHeapNode minHeapNode) {
            return distance - minHeapNode.distance;
        }

        @Override
        public String toString() {
            return "(node=" + node + ", distance=" + distance + ")";
        }
    }
}
```
