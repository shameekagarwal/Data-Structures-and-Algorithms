# Minimum Time to Visit Disappearing Nodes

- https://leetcode.com/problems/minimum-time-to-visit-disappearing-nodes/
- dijkstra along with the check of disappearing
- but due to large test cases, sometimes [usual dijkstra we studied](../../Strivers%20A2Z%20DSA%20Course%20Sheet/Step%2015:%20Graphs/Step%2015.4:%20Shortest%20Path%20Algorithms%20and%20Problems/Dijkstra's%20Shortest%20Path.md) might give tle like below
- solution - pruning i.e. do not visit neighbors of a node which has already been processed - just add a processed array - 
  ```java
  boolean[] processed = new boolean[n];

  while (!pq.isEmpty()) {

      int[] node = pq.remove();
      if (processed[node[0]]) continue;
      processed[node[0]] = true;

      // ...
  }
  ```

```java
class Solution {

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] result = new int[n];
        List<List<int[]>> graph = new ArrayList<>();
        boolean[] processed = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        pq.add(new int[]{0, 0});
        Arrays.fill(result, -1);
        result[0] = 0;

        while (!pq.isEmpty()) {

            int[] node = pq.remove();
            if (processed[node[0]]) continue;
            processed[node[0]] = true;
            
            for (int[] neighbor : graph.get(node[0])) {

                int timeTaken = node[1] + neighbor[1];

                if (timeTaken < disappear[neighbor[0]]) {

                    if (result[neighbor[0]] == -1 || result[neighbor[0]] > timeTaken) {
                        pq.add(new int[]{neighbor[0], timeTaken});
                        result[neighbor[0]] = timeTaken;
                    }
                }
            }
        }

        return result;
    }
}
```
