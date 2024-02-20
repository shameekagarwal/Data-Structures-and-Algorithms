# Sudoku Solver

- try moving to next column
- however, reset to next row first column if current column is 8
- i am maintaining a boolean matrix for if a number has been used in a row, column and box
- i numbered boxes as - 
  ```
  0 1 2
  3 4 5
  6 7 8
  ```
- so, box number is - `((i / 3) * 3) + (j / 3)`

```java
class Solution {
    
    public void solveSudoku(char[][] board) {
        
        boolean[][] isRowUsed = new boolean[9][9];
        boolean[][] isColUsed = new boolean[9][9];
        boolean[][] isMatUsed = new boolean[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int matNo = ((i / 3) * 3) + (j / 3);
                    isRowUsed[i][board[i][j] - '1'] = true;
                    isColUsed[j][board[i][j] - '1'] = true;
                    isMatUsed[matNo][board[i][j] - '1'] = true;
                }
            }
        }
        
        fill(board, 0, 0, isRowUsed, isColUsed, isMatUsed);
    }

    private boolean fill(char[][] board, int x, int y, boolean[][] isRowUsed, boolean[][] isColUsed, boolean[][] isMatUsed) {

        if (board[x][y] != '.') {
            if (y + 1 < 9) return fill(board, x, y + 1, isRowUsed, isColUsed, isMatUsed);
            else if (x + 1 < 9) return fill(board, x + 1, 0, isRowUsed, isColUsed, isMatUsed);
            else return true;
        }

        for (int i = 0; i < 9; i++) {
            int matNo = ((x / 3) * 3) + (y / 3);
            if (!isRowUsed[x][i] && !isColUsed[y][i] && !isMatUsed[matNo][i]) {

                isRowUsed[x][i] = true;
                isColUsed[y][i] = true;
                isMatUsed[matNo][i] = true;
                board[x][y] = (char) ('1' + i);

                if (y + 1 < 9) {
                    if (fill(board, x, y + 1, isRowUsed, isColUsed, isMatUsed)) {
                        return true;
                    }
                } else if (x + 1 < 9) {
                    if (fill(board, x + 1, 0, isRowUsed, isColUsed, isMatUsed)) {
                        return true;
                    }
                } else {
                    return true;
                }

                isRowUsed[x][i] = false;
                isColUsed[y][i] = false;
                isMatUsed[matNo][i] = false;
                board[x][y] = '.';
            }
        }

        return false;
    }
}
```
