# Floyd Warshall

- https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
- floyd warshall - dijkstra / bellman ford were single source
- floyd warshall is from all sources to all destinations
- it is also called a multi source shortest path algorithm
- we use adjacency matrix this time around to use dp effectively
- mark `d[i][i]` as 0, and then mark all the wights for given edges in this matrix
- if graph is undirected, mark both `d[i][j]` and `d[j][i]`
- time complexity - O(n^3)
- intuition - for going from x to y,  we need to go via another intermediary node
- so, dp can be formulated as follows - `dp[x][y] = dp[x][via] + dp[via][y]`
- how to detect a cycle? - if any `mat[i][i]` is less than 0
- we denote infinity as -1 as per question - so, do not use `i-j = Math.min(i-j, i-via + via-j)`, otherwise -1 would be assigned
- instead, write the if as shown below - either i-j == -1 or i-j is greater than (i-via + via-j)

```java
class Solution {

    public void shortest_distance(int[][] matrix) {
        
        int n = matrix.length;
        
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][via] == -1 || matrix[via][j] == -1) {
                        continue;
                    }
                    if (matrix[i][j] == -1 || matrix[i][j] > matrix[i][via] + matrix[via][j]) {
                        matrix[i][j] = matrix[i][via] + matrix[via][j];
                    }
                }
            }
        }
    }
}
``` 
