# Network Delay Time

- https://leetcode.com/problems/network-delay-time/
- a straightforward dijkstra question
- if any of the elements in the distance array are unreachable, return -1
- else, return max of them

```java
class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        
        List<List<Signal>> graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] time : times) {
            graph.get(time[0]).add(new Signal(time[1], time[2]));
        }

        int[] minTimes = new int[n + 1];
        Arrays.fill(minTimes, -1);

        PriorityQueue<Signal> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.add(new Signal(k, 0));
        minTimes[k] = 0;

        while (!pq.isEmpty()) {

            Signal signal = pq.poll();

            for (Signal neighbor : graph.get(signal.node)) {
                if (minTimes[neighbor.node] == -1 || minTimes[neighbor.node] > signal.time + neighbor.time) {
                    minTimes[neighbor.node] = signal.time + neighbor.time;
                    pq.add(new Signal(neighbor.node, minTimes[neighbor.node]));
                }
            }
        }

        int maxTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (minTimes[i] == -1) return -1;
            maxTime = Math.max(minTimes[i], maxTime);
        }
        return maxTime;
    }

    static class Signal {

        int node;
        int time;

        Signal(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}
```
