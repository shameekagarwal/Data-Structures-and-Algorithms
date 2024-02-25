# Shortest Path in Binary Matrix

- https://leetcode.com/problems/shortest-path-in-binary-matrix/
- dijkstra is not needed - we use a priority queue to help us get the smallest weight out of all, but here, weight of everything is 1
- so, simple bfs is enough
- i use visited - the first time a node is visited is the best we can do if using a queue
- another approach - use a distance matrix and only enqueue if using it helps "decrease" the distance. this second approach will be "closer" to dijkstra
- i missed out edge case `[[0]]`, return 1, remember for interviews
- how to avoid - we kind of duplicate code
  - whatever is inside bfs when traversing neighbors
  - ensure the same thing is present when adding the initial source(s) for bfs
- e.g. here
  - checking bounds (not needed because it is 0,0)
  - checking if node is 0
  - checking if node is not visited (not needed because it is the first node) 
  - checking if node being added to queue is same as destination
  - marking as visited
  - the actual addition to queue

```java
class Solution {

    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;


        boolean[][] visited = new boolean[rows][cols];
        Deque<QueueNode> queue = new ArrayDeque<>();
        
        if (grid[0][0] != 0) return -1;
        if (rows == 1 && cols == 1) return 1;
        
        visited[0][0] = true;
        queue.addLast(new QueueNode(0, 0, 1));

        while (!queue.isEmpty()) {

            QueueNode node = queue.removeFirst();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    
                    int newX = node.x + dx;
                    int newY = node.y + dy;
                    
                    if (newX < 0 || newX > rows - 1 || newY < 0 || newY > cols - 1) continue;
                    if (visited[newX][newY] || grid[newX][newY] != 0) continue;

                    if (newX == rows - 1 && newY == cols - 1) {
                        return node.distance + 1;
                    }

                    visited[newX][newY] = true;
                    queue.addLast(new QueueNode(newX, newY, node.distance + 1));
                }
            }

            // System.out.println(queue);
        }

        return -1;
    }

    static class QueueNode {

        int x;
        int y;
        int distance;

        QueueNode(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "[(" + x + ", " + y + "): " + distance + "]";
        }
    }
}
```
