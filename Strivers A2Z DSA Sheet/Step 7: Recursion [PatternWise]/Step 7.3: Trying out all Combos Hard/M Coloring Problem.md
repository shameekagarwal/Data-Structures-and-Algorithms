# M Coloring Problem

- https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
- i was struggling with - 
  - how to make recursive calls - i was trying to traverse adjacent nodes
  - what should be the base case - all elements in color are non zero (i.e. all nodes have been colored)
- but that approach was wrong
- it was as straightforward as iterating from 0 to N - 1, and return true if current node reaches N

```java
class solve {

    public boolean graphColoring(boolean graph[][], int m, int n) {
        int[] color = new int[n];
        return recurse(graph, m, n, color, 0);
    }
    
    private boolean recurse(boolean graph[][], int m, int n, int[] color, int node) {
        
        if (node == n) return true;

        for (int i = 1; i <= m; i++) {

            boolean isAdjacentNodeColoredSame = false;
            for (int j = 0; j < n; j++) {
                if (graph[node][j] && (color[j] == i)) {
                    isAdjacentNodeColoredSame = true;
                    break;
                }
            }
            if (isAdjacentNodeColoredSame) continue;

            color[node] = i;
            if (recurse(graph, m, n, color, node + 1)) {
                return true;
            }
            color[node] = 0;
        }
        
        return false;
    }
}
```
