# Number of Ways to Arrive at Destination

- https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
- common mistake - if find 3 ways to reach a destination, does not mean answer would be 3
- why - e.g. we reach destination from node x in 1 way, but we can reach x itself in 3 ways
- final answer for reaching destination would be 3
- so, we need to add the number of ways of reaching x when calculating number of ways of reaching destination
- simply adding 1 will not work
- so, along with initializing distance with 0 for source, initialize number of ways for source with 1

```java
class Solution {

    private static final int MODULO = 1000000007;

    public int countPaths(int n, int[][] roads) {

        /* construct graph */
        
        List<List<Road>> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(new Road(road[1], road[2]));
            graph.get(road[1]).add(new Road(road[0], road[2]));
        }

        /* ============================= */

        /* dijkstra */

        long[] times = new long[n];
        Arrays.fill(times, -1);
        
        int[] numberOfWays = new int[n];

        PriorityQueue<Road> minHeap = new PriorityQueue<>();
        minHeap.add(new Road(0, 0));
        numberOfWays[0] = 1;
        times[0] = 0;

        while (!minHeap.isEmpty()) {

            Road road = minHeap.poll();

            for (Road neighbor : graph.get(road.destination)) {
                if (times[neighbor.destination] == -1 || times[neighbor.destination] > road.time + neighbor.time) {
                    times[neighbor.destination] = road.time + neighbor.time;
                    numberOfWays[neighbor.destination] = numberOfWays[road.destination];
                    minHeap.add(new Road(neighbor.destination, times[neighbor.destination]));
                } else if (times[neighbor.destination] == road.time + neighbor.time) {
                    numberOfWays[neighbor.destination] = (numberOfWays[neighbor.destination] + numberOfWays[road.destination]) % MODULO;
                }
            }
        }

        return numberOfWays[n - 1];

        /* ============================= */
    }

    static class Road implements Comparable<Road> {

        int destination;
        long time;

        Road(int destination, long time) {
            this.destination = destination;
            this.time = time;
        }

        @Override
        public int compareTo(Road road) {
            if (time > road.time) return 1;
            return -1;
        }

        @Override
        public String toString() {
            return "Road(destination=" + destination + ", time=" + time + ")";
        }
    }
}
```
