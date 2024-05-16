# Minimum Time to Collect All Apples in a Tree

- https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

```java
class Solution {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return _minTime(0, -1, graph, hasApple).contribution;
    }

    private State _minTime(int current, int parent, List<List<Integer>> graph, List<Boolean> hasApple) {

        State state = new State();
        state.hasApples = hasApple.get(current);

        for (int neighbor : graph.get(current)) {

            if (neighbor == parent) continue;
            
            State childState = _minTime(neighbor, current, graph, hasApple);
            state.hasApples |= childState.hasApples;
            state.contribution += childState.contribution + (childState.hasApples ? 2 : 0);
        }

        return state;
    }

    static class State {

        boolean hasApples;
        int contribution;
    }
}
```
