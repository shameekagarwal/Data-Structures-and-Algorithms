# Bellman Ford

- https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
- dijkstra fails for negative edges
- bellman ford can detect negative cycles as well
- if graph is undirected, add both edges to make it directed
- we need to perform the algorithm n - 1 times on all edges
- why n - 1 iterations - that is the longest possible path in a graph of n nodes - after that, we start cycling around
- how to detect negative cycles - perform an extra nth iteration. if the distance array gets updated even on the nth iteration, we can conclude that a negative cycle exists

```java
class Solution {

    private static final int INF = (int) Math.pow(10, 8);

    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        
        int[] distance = new int[V];
        Arrays.fill(distance, INF);
        distance[S] = 0;
        
        for (int i = 0; i < V - 1; i++) {
            for (List<Integer> edge : edges) {
                if (distance[edge.get(0)] != INF && distance[edge.get(1)] > distance[edge.get(0)] + edge.get(2)) {
                    distance[edge.get(1)] = distance[edge.get(0)] + edge.get(2);
                }
            }
        }
        
        for (List<Integer> edge : edges) {
            if (distance[edge.get(0)] != INF && distance[edge.get(1)] > distance[edge.get(0)] + edge.get(2)) {
                return new int[]{-1};
            }
        }

        return distance;
    }
}
```
