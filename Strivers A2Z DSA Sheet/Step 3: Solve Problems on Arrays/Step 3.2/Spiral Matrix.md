# Spiral Matrix

- https://leetcode.com/problems/spiral-matrix/
- note the checking of condition at every point
- in interview, make functions like is valid etc
- both time and space complexity - O(n ^ 2)

```
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int topRow = 0;
        int bottomRow = matrix.length - 1;
        int leftColumn = 0;
        int rightColumn = matrix[0].length - 1;

        while (true) {

            for (int i = leftColumn; i <= rightColumn; i++) {
                result.add(matrix[topRow][i]);
            }
            topRow += 1;
            if (topRow > bottomRow || leftColumn > rightColumn) break;

            for (int i = topRow; i <= bottomRow; i++) {
                result.add(matrix[i][rightColumn]);
            }
            rightColumn -= 1;
            if (topRow > bottomRow || leftColumn > rightColumn) break;

            for (int i = rightColumn; i >= leftColumn; i--) {
                result.add(matrix[bottomRow][i]);
            }
            bottomRow -= 1;
            if (topRow > bottomRow || leftColumn > rightColumn) break;

            for (int i = bottomRow; i >= topRow; i--) {
                result.add(matrix[i][leftColumn]);
            }
            leftColumn += 1;
            if (topRow > bottomRow || leftColumn > rightColumn) break;
        }

        return result;
    }
}
```
