# Minimum Height Trees

- https://leetcode.com/problems/minimum-height-trees/
- brute force - calculate height considering all nodes as root
- time complexity - O(n^2)
- optimal - lot of new concepts
- concept 1 - two nodes will have only path between them
- concept 2 - height = distance from centroid to leaves of graph
- concept 3 - answers will be "centroids" of graph
- concept 4 - a tree like graph can only have 1 or 2 centroids
- proof of concept 4 - 
  - if i want 3 centroids
  - all 3 centroids will have to be at same distance from all other nodes and from each other
  - e.g. if we have just A <-> B <-> C i.e. A, B, C are centroids
  - then all nodes at x distance from A are at distance x+2 from C
  - so, only possible to have 3 centroids if all centroids form a cycle
  - cycles are not possible unless 
- concept 5 - leaf in a tree means degree is 1
- concept 6 - we solve it like topological sort kahn's algorithm - keep adding nodes with degree 1 every time, and when a node is being processed, remove it from the set of neighbors
- concept 7 - we add when degree is 1 to the queue - maybe because this edge represents "how this current node reaches towards the centroid"

```java
class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Set<Integer>> graph = constructGraph(n, edges);

        Deque<int[]> queue = new ArrayDeque<>();

        int[] levels = new int[n];

        for (int i = 0; i < n; i++) {

            if (graph.get(i).size() == 1) {
                queue.add(new int[]{i, 1});
            }
        }

        while (!queue.isEmpty()) {

            int[] node = queue.removeFirst();
            levels[node[0]] = node[1];

            for (int neighbor : graph.get(node[0])) {

                graph.get(neighbor).remove(node[0]);

                if (graph.get(neighbor).size() == 1) {
                    queue.add(new int[]{neighbor, node[1] + 1});
                }
            }
        }

        int maxLevel = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (maxLevel < levels[i]) {
                maxLevel = levels[i];
                result = new ArrayList<>();
                result.add(i);
            } else if (maxLevel == levels[i]) {
                result.add(i);
            }
        }

        return result;
    }

    private List<Set<Integer>> constructGraph(int n, int[][] edges) {

        List<Set<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }
}
```
