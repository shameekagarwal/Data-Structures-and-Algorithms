# Number of Ways to Arrive at Destination

- https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
- common mistake - if find 3 ways to reach a destination, does not mean answer would be 3
- why - e.g. we reach destination from node x in 1 way, but we can reach x itself in 3 ways
- final answer for reaching destination would be 3
- so, we need to add the number of ways of reaching x when calculating number of ways of reaching destination
- simply adding 1 will not work
- so, along with initializing distance with 0 for source, initialize number of ways for source with 1
- another point - it might happen that a node is processed multiple times with different weights - 
    - e.g. we have 1 -> 3 -> 5 and 1 -> 5
    - first path has weight 7, second path has weight 10
    - so, however, 1 -> 5 will be added first to the pq, and stay there till the end
    - 1 -> 3 -> 5 will be added later but removed first
    - we will just have to discard it

```java
class Solution {

    private static final int MOD = 1_000_000_007;

    public int countPaths(int n, int[][] roads) {

        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(new Node(road[1], road[2]));
            graph.get(road[1]).add(new Node(road[0], road[2]));
        }

        long[] distance = new long[n];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[0] = 0;

        int[] noOfWays = new int[n];
        noOfWays[0] = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0L));

        while (!pq.isEmpty()) {

            Node node = pq.remove();
            if (node.distance > distance[node.vertex]) continue;

            // System.out.printf("processing %d\n", node.vertex);

            for (Node neighbor : graph.get(node.vertex)) {

                if (neighbor.distance + distance[node.vertex] < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = neighbor.distance + distance[node.vertex];
                    noOfWays[neighbor.vertex] = noOfWays[node.vertex];
                    pq.add(new Node(neighbor.vertex, distance[neighbor.vertex]));
                } else if (neighbor.distance + distance[node.vertex] == distance[neighbor.vertex]) {
                    noOfWays[neighbor.vertex] = addMod(noOfWays[neighbor.vertex], noOfWays[node.vertex]);
                }
            }

            // System.out.println(Arrays.toString(distance));
            // System.out.println(Arrays.toString(noOfWays));
            // System.out.println();
        }

        return noOfWays[n - 1];
    }

    private static int addMod(int a, int b) {
        return (int) ((0L + a + b) % MOD);
    }

    static class Node implements Comparable<Node> {

        int vertex;
        long distance;

        Node(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "(" + vertex + ", " + distance + ")";
        }

        @Override
        public int compareTo(Node node) {
            return distance > node.distance ? 1 : -1;
        }
    }
}
```
