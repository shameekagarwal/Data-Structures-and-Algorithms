# Make a Square with the Same Color

- https://leetcode.com/problems/make-a-square-with-the-same-color/
- there are 4 2*2 squares
- if all of them have 2 black squares - not possible, else possible

```java
class Solution {

    public boolean canMakeSquare(char[][] grid) {

        int blackOne = countBlack(grid, 0, 1, 0, 1);
        int blackTwo = countBlack(grid, 1, 2, 0, 1);
        int blackThree = countBlack(grid, 0, 1, 1, 2);
        int blackFour = countBlack(grid, 1, 2, 1, 2);
        
        return !(blackOne == 2 && blackTwo == 2 && blackThree == 2 && blackFour == 2);
    }
    
    private int countBlack(char[][] grid, int startRow, int endRow, int startCol, int endCol) {
        
        int count = 0;
        
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (grid[i][j] == 'B') {
                    count += 1;
                }
            }
        }
        
        return count;
    }
}
```
