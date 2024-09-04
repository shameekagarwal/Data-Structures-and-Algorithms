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
- note how we are filling the sudoku - from left to right, then go to next row if we reach the right end

```java
class Solution {

    public void solveSudoku(char[][] board) {

        boolean[][] rowsFilled = new boolean[9][9];
        boolean[][] colsFilled = new boolean[9][9];
        boolean[][] boxesFilled = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') continue;

                int mark = board[i][j] - '1';
                int box = getBox(i, j);

                rowsFilled[i][mark] = true;
                colsFilled[j][mark] = true;
                boxesFilled[box][mark] = true;
            }
        }

        solveSudoku(board, 0, 0, rowsFilled, colsFilled, boxesFilled);
    }

    private boolean solveSudoku(char[][] board, 
        int row, int col, 
        boolean[][] rowsFilled, boolean[][] colsFilled, boolean[][] boxesFilled) {

        if (row == 9) return true;
        if (col == 9) return solveSudoku(board, row + 1, 0, rowsFilled, colsFilled, boxesFilled);
        if (board[row][col] != '.') return solveSudoku(board, row, col + 1, rowsFilled, colsFilled, boxesFilled);

        for (int mark = 1; mark <= 9; mark++) {

            if (rowsFilled[row][mark - 1]) continue;
            if (colsFilled[col][mark - 1]) continue;

            int box = (row / 3 * 3) + (col / 3);
            if (boxesFilled[box][mark - 1]) continue;

            board[row][col] = (char) ('0' + mark);
            boxesFilled[box][mark - 1] = true;
            rowsFilled[row][mark - 1] = true;
            colsFilled[col][mark - 1] = true;

            if (solveSudoku(board, row, col + 1, rowsFilled, colsFilled, boxesFilled)) {
                return true;
            }

            board[row][col] = '.';
            boxesFilled[box][mark - 1] = false;
            rowsFilled[row][mark - 1] = false;
            colsFilled[col][mark - 1] = false;
        }

        return false;
    }

    private int getBox(int row, int col) {
        return (row / 3 * 3) + (col / 3);
    }
}
```
