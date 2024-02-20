# Word Search

- https://leetcode.com/problems/word-search/
- note that vis is needed so that same letter is not used twice - we cant go 0,0 -> 0,1 and again 0,0
- i think the or should work as a short circuit as a little optimization 

```java
class Solution {

    public boolean exist(char[][] board, String word) {
        
        boolean[][] vis = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (recurse(board, i, j, word.toCharArray(), 0, vis)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean recurse(char[][] board, int x, int y, char[] word, int idx, boolean[][] vis) {

        if (idx == word.length) return true;

        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
        if (vis[x][y]) return false;
        if (board[x][y] != word[idx]) return false;

        vis[x][y] = true;

        if (
            recurse(board, x + 1, y, word, idx + 1, vis) || 
            recurse(board, x - 1, y, word, idx + 1, vis) || 
            recurse(board, x, y + 1, word, idx + 1, vis) || 
            recurse(board, x, y - 1, word, idx + 1, vis)
        ) {
            return true;
        }

        vis[x][y] = false;

        return false;
    }
}
```
