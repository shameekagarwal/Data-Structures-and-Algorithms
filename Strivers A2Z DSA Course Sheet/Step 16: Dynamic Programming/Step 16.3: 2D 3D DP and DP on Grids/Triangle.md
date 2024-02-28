# Triangle

- https://leetcode.com/problems/triangle/
- recursive case - time complexity - 
   - 1st goes 2, 2nd goes 4, and so on... 2 + 4 + 8 + ... so, sum of gp

```java
class Solution {

    private List<List<Integer>> triangle;

    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        return minimumTotal(0, 0);
    }

    int minimumTotal(int row, int col) {
        if (row == triangle.size()) return 0;
        return Math.min(minimumTotal(row + 1, col), minimumTotal(row + 1, col + 1)) + triangle.get(row).get(col);
    }
}
```

- tabular + space optimized -
- notice how it is exactly written like the recursive variant

```java
class Solution {

    private List<List<Integer>> triangle;

    public int minimumTotal(List<List<Integer>> triangle) {
        
        int[] previousRow = listToArray(triangle.get(triangle.size() - 1));

        for (int i = triangle.size() - 2; i > -1; i--) {
            
            int[] currentRow = new int[i + 1];
            
            for (int j = 0; j < i + 1; j++) {
                currentRow[j] = Math.min(previousRow[j], previousRow[j + 1]) + triangle.get(i).get(j);
            }

            previousRow = currentRow;
        }

        int min = Integer.MAX_VALUE;
        for (int i : previousRow) {
            min = Math.min(i, min);
        }
        return min;
    }

    private int[] listToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
```
