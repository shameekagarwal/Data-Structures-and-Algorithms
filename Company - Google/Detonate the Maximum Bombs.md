# Detonate the Maximum Bombs

- we need distance between the two centers
- now, if bomb a's radius is greater than this distance, bomb a can blast bomb b
- rookie mistake i made - this property is not commutative - if bomb a can blast b, it does not mean bomb b can blast bomb a. i was using a dsu, which we cannot use
- so, run a normal bfs for all bombs
- complexity - n^3?
  - for all bombs
  - run bfs
  - finding neighbors itself is n
- note, doubt - can i drop it to an n^2 by pre calculating the edges?
- imp takeaway - dsu works on undirected graphs only?

```java
class Solution {

    public int maximumDetonation(int[][] bombs) {

        int result = 1;

        for (int i = 0; i < bombs.length; i++) {
            result = Math.max(result, explode(i, bombs));
        }

        return result;
    }

    private int explode(int src, int[][] bombs) {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(src);

        boolean[] visited = new boolean[bombs.length];
        visited[src] = true;

        while (!queue.isEmpty()) {

            int node = queue.removeFirst();

            for (int i = 0; i < bombs.length; i++) {

                if (visited[i]) continue;

                if (canExplode(bombs[node], bombs[i])) {
                    visited[i] = true;
                    queue.addLast(i);
                }
            }
        }

        int explosions = 0;

        for (int i = 0; i < bombs.length; i++) {
            explosions += visited[i] ? 1 : 0;
        }

        return explosions;
    }

    private boolean canExplode(int[] bombA, int[] bombB) {
        double distance = calculateDistanceBetweenBombs(bombA, bombB);
        return distance <= bombA[2];
    }

    private double calculateDistanceBetweenBombs(int[] bombA, int[] bombB) {

        return Math.sqrt(
            Math.pow(bombA[0] - bombB[0], 2) +
            Math.pow(bombA[1] - bombB[1], 2)
        );
    }
}
```
