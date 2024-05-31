# Find Number of Coins to Place in Tree Nodes

- https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes/
- first i make an undirected graph and then a directed graph from this - because question is for a directed graph but input is in undirected form - it is a tree rooted at 0, to there is concept of parent and children
- state maintains 3 greatest positive, 2 smallest negatives in subtree and size of subtree
- if subtree size is < 3, simply return 1
- else, chose one of
  - 0
  - product of the 3 positives
    - multiply all three positives in the priority queue by serially iterating over it
  - product of biggest positive and the 2 negatives
    - obtain largest positive by serially iterating over the positive priority queue
    - multiply both negatives inside the negative priority queue

```java
class Solution {

    public long[] placedCoins(int[][] edges, int[] cost) {

        int n = cost.length;

        List<List<Integer>> undirectedGraph = constructUndirectedGraph(edges, n);
        List<List<Integer>> directedGraph = constructDirectedGraph(undirectedGraph, n);

        long[] result = new long[n];
        dfs(directedGraph, 0, cost, result);
        return result;
    }

    private State dfs(List<List<Integer>> graph, int node, int[] cost, long[] result) {

        State state = new State();
        state.addCost(cost[node]);

        for (int neighbor : graph.get(node)) {

            State subtreeCost = dfs(graph, neighbor, cost, result);
            state.combine(subtreeCost);
        }

        result[node] = state.calculateMax();

        return state;
    }

    private List<List<Integer>> constructUndirectedGraph(int[][] edges, int n) {

        List<List<Integer>> graph = initializeGraph(n);

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }

    private List<List<Integer>> constructDirectedGraph(List<List<Integer>> undirectedGraph, int n) {

        List<List<Integer>> directedGraph = initializeGraph(n);

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        queue.addLast(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
           
            int node = queue.removeFirst();

            for (int neighbor : undirectedGraph.get(node)) {
                if (!visited[neighbor]) {
                    queue.addLast(neighbor);
                    visited[neighbor] = true;
                    directedGraph.get(node).add(neighbor);
                }
            }
        }

        return directedGraph;
    }

    private List<List<Integer>> initializeGraph(int n) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        return graph;
    }

    static class State {

        PriorityQueue<Integer> positiveCosts = new PriorityQueue<Integer>();
        PriorityQueue<Integer> negativeCosts = new PriorityQueue<Integer>(Collections.reverseOrder());
        int treeSize = 1;

        void addCost(int cost) {
            if (cost < 0) {
                negativeCosts.add(cost);
            } else {
                positiveCosts.add(cost);
            }
        }

        void combine(State state) {

            for (int value : state.positiveCosts) {

                positiveCosts.add(value);

                if (positiveCosts.size() > 3) {
                    positiveCosts.remove();
                }
            }

            for (int value : state.negativeCosts) {

                negativeCosts.add(value);

                if (negativeCosts.size() > 2) {
                    negativeCosts.remove();
                }
            }

            treeSize += state.treeSize;
        }

        long calculateMax() {

            if (treeSize < 3) {
                return 1;
            }

            long result = 0;

            if (positiveCosts.size() == 3) {

                long possible = 1;

                for (int cost : positiveCosts) {
                    possible *= cost;
                }

                result = Math.max(result, possible);
            }

            if (positiveCosts.size() >= 1 && negativeCosts.size() == 2) {
                
                long possible = 1;

                for (int cost : positiveCosts) {
                    possible = Math.max(possible, cost);
                }

                for (int cost : negativeCosts) {
                    possible *= cost;
                }

                result = Math.max(result, possible);
            }

            return result;
        }

        @Override
        public String toString() {
            return "(" + positiveCosts + ", " + negativeCosts + ", " + treeSize + ")";
        }
    }
}
```
