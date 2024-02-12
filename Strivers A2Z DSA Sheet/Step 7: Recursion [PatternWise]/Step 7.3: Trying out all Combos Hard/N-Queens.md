# N-Queens

- https://leetcode.com/problems/n-queens/
- visited logic - since we fill the board from left to right, we only need to check - 
  - north west diagonal
  - south west diagonal
  - row
- since we fill one column after another, no need to check current column / anything on the right
- visited of row is easy to maintain
- for visited of diagonal -
  - what the matrix looks like - 
    ```
    (0,0) (0,1) (0,2)
    (1,0) (1,1) (1,2)
    (2,0) (2,1) (2,2)
    ```
  - diagonal 1 - 
    ```
    (2,0)
    (1,0) (2,1)
    (0,0) (1,1) (2,2)
    (0,1) (1,2)
    (0,2)
    ```
  - after looking above - correlation between them is i - j is same. to keep everything positive, add 2 i.e. n - 1. so, formula = `i - j + n - 1`
  - diagonal 2 -
    ```
    (0,0)
    (0,1) (1,0)
    (0,2) (1,1) (2,0)
    (1,2) (2,1)
    (2,2)
    ```
  - after looking above - correlation between them is i + j is same. so, formula = `i + j`

```java
class Solution {

    private static final char EMPTY = '.';
    private static final char QUEEN = 'Q';

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<char[]> board = getEmptyBoard(n);
        boolean[] visRow = new boolean[n];
        boolean[] visDiagonalOne = new boolean[2 * n - 1];
        boolean[] visDiagonalTwo = new boolean[2 * n - 1];
        recurse(result, n, 0, board, visRow, visDiagonalOne, visDiagonalTwo);
        return result;
    }

    private void recurse(List<List<String>> result, int n, int col, List<char[]> board, boolean[] visRow, boolean[] visDiagonalOne, boolean[] visDiagonalTwo) {

        if (col == board.size()) {
            List<String> finalBoard = board.stream()
                .map((s -> new String(s)))
                .collect(Collectors.toList());
            result.add(finalBoard);
            return;
        }

        for (int i = 0; i < n; i++) {

            if (!visRow[i] && !visDiagonalOne[i + col] && !visDiagonalTwo[i - col + n - 1]) {
                
                visRow[i] = true;
                visDiagonalOne[i + col] = true;
                visDiagonalTwo[i - col + n - 1] = true;
                
                board.get(i)[col] = QUEEN;
                recurse(result, n, col + 1, board, visRow, visDiagonalOne, visDiagonalTwo);
                board.get(i)[col] = EMPTY;

                visRow[i] = false;
                visDiagonalOne[i + col] = false;
                visDiagonalTwo[i - col + n - 1] = false;
            }
        }
    }

    private List<char[]> getEmptyBoard(int n) {

        char[] row = new char[n];
        List<char[]> board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            row[i] = EMPTY;
        }

        for (int i = 0; i < n; i++) {
            board.add(Arrays.copyOfRange(row, 0, n));
        }

        return board;
    }
}
```
