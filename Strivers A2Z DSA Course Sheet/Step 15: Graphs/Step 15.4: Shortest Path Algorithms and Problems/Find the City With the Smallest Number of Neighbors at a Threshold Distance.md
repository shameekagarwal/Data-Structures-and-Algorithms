# Find the City With the Smallest Number of Neighbors at a Threshold Distance

- https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
- just implement [floyd warshall](./Floyd%20Warshall.md), and then implement what the question asks
- graph is undirected - so add edge for both i,j and j,i
- i use <= in `citiesAtLesserThanThresholdFromFrom <= citiesAtLesserThanThreshold` to help overwrite with greater cities when there exist multiple answers
- question does not state negative weights, so actually should be doable using dijkstra as well

```java
class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
        int[][] matrix = new int[n][n];

        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                if (from != to) {
                    matrix[from][to] = -1;
                }
            }
        }

        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = edge[2];
            matrix[edge[1]][edge[0]] = edge[2];
        }

        for (int via = 0; via < n; via++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {

                    if (matrix[from][via] == -1 || matrix[via][to] == -1) continue;

                    if (matrix[from][to] == -1 || matrix[from][to] > matrix[from][via] + matrix[via][to]) {
                        matrix[from][to] = matrix[from][via] + matrix[via][to];
                    }
                }
            }
        }

        int city = -1;
        int citiesAtLesserThanThreshold = n + 1;

        for (int from = 0; from < n; from++) {

            int citiesAtLesserThanThresholdFromFrom = 0;
            // System.out.printf("%d: %s\n", from, Arrays.toString(matrix[from]));

            for (int to = 0; to < n; to++) {
                if (from != to && matrix[from][to] != -1 && matrix[from][to] <= distanceThreshold) {
                    citiesAtLesserThanThresholdFromFrom += 1;
                }
            }

            if (citiesAtLesserThanThresholdFromFrom <= citiesAtLesserThanThreshold) {
                citiesAtLesserThanThreshold = citiesAtLesserThanThresholdFromFrom;
                city = from;
            }
        }

        return city;
    }
}
```
