# Unique Paths

- https://leetcode.com/problems/unique-paths/
- recursive solution - time complexity - 2^(m*n)
- in interview, show recurring solution as well

```java
class Solution {

    private int m;
    private int n;

    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        return _uniquePaths(0, 0);
    }

    private int _uniquePaths(int currentX, int currentY) {

        if (!isValid(currentX, currentY)) {
            return 0;
        } else if (currentX == m - 1 && currentY == n - 1) {
            return 1;
        } else {
            return _uniquePaths(currentX + 1, currentY) + _uniquePaths(currentX, currentY + 1);
        }
    }

    private boolean isValid(int x, int y) {
        return x > -1 && x < m && y > -1 && y < n;
    }
}
```

- tabular approach with optimal space

```java
class Solution {

    public int uniquePaths(int m, int n) {

        int[] prevRow = new int[n];

        for (int i = 0; i < m; i++) {

            int[] row = new int[n];

            for (int j = 0; j < n; j++) {
                
                if (i == 0 && j == 0) {
                    row[0] = 1;
                    continue;
                }
                
                int waysFromAbove = i == 0 ? 0 : prevRow[j];
                int waysFromLeft = j == 0 ? 0 : row[j - 1];
                row[j] = waysFromAbove + waysFromLeft;
            }

            prevRow = row;
        }

        return prevRow[n - 1];
    }
}
```
