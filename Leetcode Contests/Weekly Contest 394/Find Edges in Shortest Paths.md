# Find Edges in Shortest Paths

- https://leetcode.com/problems/find-edges-in-shortest-paths/
- step 1 - we modify dijkstra a little - 
  - add to pq for `<=`, not just `<`
  - to the pq, apart from node and weight, we add the "edge used"
- now, when popping from priority queue and before pruning - check if distance of popped is same as min distance array maintained in dijkstra. if it is, add the edge
- now, for every node, we will have the edges used to get to it from 0
- now, we will perform recursion - 
  - from n - 1, mark all edges used to get to it as true, and go back using these edges
  - keep repeating this process
  - base case - we reach the node 0
  - note - i used a visited as well, to remove repeated calls for the same node

```java
class Solution {

    public boolean[] findAnswer(int n, int[][] edges) {
        
        List<List<int[]>> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        int e = edges.length;
        
        for (int i = 0; i < e; i++) {

            int[] edge = edges[i];

            graph.get(edge[0]).add(new int[]{edge[1], edge[2], i});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2], i});
        }

        List<List<Integer>> used = dijkstra(graph, n);
        
        boolean[] result = new boolean[e];
        boolean[] visited = new boolean[n];
        
        recurse(used, n - 1, edges, result, visited);
        
        return result;
    }

    private List<List<Integer>> dijkstra(List<List<int[]>> graph, int n) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0, -1});

        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        distance[0] = 0;

        List<List<Integer>> used = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            used.add(new ArrayList<>());
        }
        
        boolean[] visited = new boolean[n];
        
        while (!pq.isEmpty()) {
            
            int[] node = pq.remove();
            
            if (node[1] == distance[node[0]]) {
                used.get(node[0]).add(node[2]);
            }

            if (visited[node[0]]) continue;
            
            visited[node[0]] = true;
            
            for (int[] neighbor : graph.get(node[0])) {
                if (distance[neighbor[0]] == -1 || distance[neighbor[0]] >= distance[node[0]] + neighbor[1]) {
                    pq.add(new int[]{neighbor[0], distance[node[0]] + neighbor[1], neighbor[2]});
                    distance[neighbor[0]] = distance[node[0]] + neighbor[1];
                }
            }
        }
        
        return used;
    }
    
    private void recurse(List<List<Integer>> used, int node, int[][] edges, boolean[] result, boolean[] visited) {
        
        if (visited[node]) return;
        if (node == 0) return;

        visited[node] = true;
        
        for (int edgeIdx : used.get(node)) {

            result[edgeIdx] = true;
            int[] edge = edges[edgeIdx];

            int neighbor = edge[0] == node ? edge[1] : edge[0];

            recurse(used, neighbor, edges, result, visited);
        }
    }
}
```
