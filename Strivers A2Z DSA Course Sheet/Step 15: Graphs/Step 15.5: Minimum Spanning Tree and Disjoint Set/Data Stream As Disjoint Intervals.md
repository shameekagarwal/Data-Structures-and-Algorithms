# Data Stream As Disjoint Intervals

- https://leetcode.com/problems/data-stream-as-disjoint-intervals/

## TreeSet

- add - O(logN) - because bst like structure underneath
- get intervals - O(N) - iterate one by one - this will be in order. then, accordingly form intervals
- vvimp - see `toArray` function to easily convert list to array

```java
import java.util.*;

public class Solution {

    public static class DisjointIntervals {

        private Set<Integer> set;

        public DisjointIntervals() {
            set = new TreeSet<>();
        }

        public void addInteger(int val) {
            set.add(val);
        }

        public List<List<Integer>> getDisjointIntervals() {
            
            List<List<Integer>> result = new ArrayList<>();
            
            for (int element : set) {
                if (result.isEmpty()) {
                    result.add(constructList(element, element));
                } else {
                    List<Integer> lastInterval = result.get(result.size() - 1);
                    int lastIntervalEnd = lastInterval.get(1);
                    if (lastIntervalEnd == element - 1) {
                        lastInterval.set(1, element);
                    } else {
                        result.add(constructList(element, element));
                    }
                }
            }

            return result;
        }

        private List<Integer> constructList(int... a) {
            List<Integer> list = new ArrayList<>();
            for (int i : a) {
                list.add(i);
            }
            return list;
        }
    }
}
```

- solution using bfs - 
- find all components in graph
- find number of edges in each component
- find number of nodes in each component
- excess edges in a component = (number of edges in a component) - (number of nodes in a component)
- now, number of edges we need = number of components - 1
- if excess edges is >= above, return above, else return -1

```java
class Solution {

    public int makeConnected(int n, int[][] connections) {
        
        List<List<Integer>> graph = constructGraph(n, connections);

        int[] color = new int[n];
        int currentColor = 1;

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                bfs(i, currentColor, color, graph);
                currentColor += 1;
            }
        }

        currentColor -= 1;

        Map<Integer, Integer> componentEdges = new HashMap<>();
        Map<Integer, Integer> componentSize = new HashMap<>();

        for (int i = 0; i < n; i++) {
            componentEdges.put(color[i], componentEdges.getOrDefault(color[i], 0) + graph.get(i).size());
            componentSize.put(color[i], componentSize.getOrDefault(color[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : componentEdges.entrySet()) {
            entry.setValue(entry.getValue() / 2);
        }

        int excessEdges = 0;

        for (int i = 1; i <= currentColor; i++) {
            excessEdges += componentEdges.get(i) - (componentSize.get(i) - 1);
        }

        return excessEdges >= (currentColor - 1) ? currentColor - 1 : -1;
    }

    private void bfs(int src, int currentColor, int[] color, List<List<Integer>> graph) {
        
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(src);
        color[src] = currentColor;

        while (!queue.isEmpty()) {
            
            int node = queue.removeFirst();
            
            for (int neighbor : graph.get(node)) {
                if (color[neighbor] == 0) {
                    color[neighbor] = currentColor;
                    queue.addLast(neighbor);
                }
            }
        }
    }

    private List<List<Integer>> constructGraph(int n, int[][] connections) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }

        return graph;
    }
}
```
