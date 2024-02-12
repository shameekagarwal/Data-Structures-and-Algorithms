# Rat in a Maze

- https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
- we need to maintain a vis so that we do not get stuck in an infinite loop of up down up down, just like [Word Search](./Word%20Search.md)
- i keep forgetting string builder's stl, remember - `.delete(start, end)` (start inclusive, end exclusive)

```java
class Solution {
    
    private static Map<Character, int[]> directions = Map.of(
        'D', new int[]{1, 0},
        'L', new int[]{0, -1},
        'R', new int[]{0, 1},
        'U', new int[]{-1, 0}
    );

    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> result = new ArrayList<>();
        boolean[][] vis = new boolean[n][n];
        StringBuilder path = new StringBuilder();
        recurse(result, m, n, 0, 0, vis, path);
        return result;
    }
    
    private static void recurse(ArrayList<String> result, int[][] m, int n, int x, int y, boolean[][] vis, StringBuilder path) {
        
        if (x == -1 || y == -1 || x == n || y == n) return;
        if (vis[x][y]) return;
        if (m[x][y] == 0) return;

        if (x == n - 1 && y == n - 1) {
            result.add(path.toString());
        }
        
        vis[x][y] = true;
        for (Map.Entry<Character, int[]> direction : directions.entrySet()) {
            path.append(direction.getKey());
            recurse(result, m, n, x + direction.getValue()[0], y + direction.getValue()[1], vis, path);
            path.delete(path.length() - 1, path.length());
        }
        vis[x][y] = false;
    }
}
```
