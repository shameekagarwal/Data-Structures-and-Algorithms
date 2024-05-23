# Network Delay Time

- https://leetcode.com/problems/network-delay-time/
- a straightforward dijkstra question
- if any of the elements in the distance array are unreachable, return -1
- else, return max of them
- note - in addition to dijkstra, i also added pruning logic using visited boolean array

```java
class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] time : times) {
            graph.get(time[0] - 1).add(new int[]{time[1] - 1, time[2]});
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        distance[k - 1] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.add(new int[]{k - 1, 0});
        boolean[] visited = new boolean[n];

        while (!minHeap.isEmpty()) {

            int[] node = minHeap.remove();
            if (visited[node[0]]) continue;

            visited[node[0]] = true;

            for (int[] neighbor : graph.get(node[0])) {

                if (distance[neighbor[0]] == -1 || distance[neighbor[0]] > node[1] + neighbor[1]) {
                    distance[neighbor[0]] = node[1] + neighbor[1];
                    minHeap.add(new int[]{neighbor[0], distance[neighbor[0]]});
                }
            }
        }

        int result = 0;

        for (int path : distance) {
            if (path == -1) return -1;
            result = Math.max(path, result);
        }

        return result;
    }
}
```
