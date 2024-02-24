# Flood Fill

- https://leetcode.com/problems/flood-fill/
- mark first node as the color, then start traversing in all 4 directions, only if value is same as initial color of source cell
- using dfs this time

```java
class Solution {

    private static final List<int[]> directions = List.of(
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1}
    );

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        
        image = createDeepCopy(image);

        int initialColor = image[sr][sc];
        if (initialColor == color) return image;

        image[sr][sc] = color;

        dfs(image, sr, sc, initialColor, color);

        return image;
    }

    private void dfs(int[][] image, int r, int c, int initialColor, int color) {

        for (int[] direction : directions) {
 
            int x = r + direction[0];
            int y = c + direction[1];

            if (x > -1 && x < image.length && y > -1 && y < image[0].length) {

                if (image[x][y] == initialColor) {
                    image[x][y] = color;
                    dfs(image, x, y, initialColor, color);
                }
            }
        }
    }

    private int[][] createDeepCopy(int[][] image) {

        int[][] copy = new int[image.length][];

        for (int i = 0; i < copy.length; i++) {
            copy[i] = Arrays.copyOfRange(image[i], 0, image[i].length);
        }

        return copy;
    }
}
```
