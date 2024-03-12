# Pascal's Triangle

## NCR

- calculating ncr efficiently without involving floating point division etc -
  ```
  n   (n - 1)   (n - 2)          (n - r + 1)
  - * ------- * ------- * .... * ----------
  1   2         3                r
  ```
- this way, by the time 3 comes in denominator, one of n, n - 1, n - 2 should be able to divide it completely

## Value at a Position

- let we want value at row r and column c
- answering number at a particular position - (r-1)C(c-1)
- in interview, we cannot come up with this directly - derive it from [particular row](#particular-row)

## Particular Row

- printing a particular row - e.g. 9th row is 1 8 28 56 70 56 28 8 1
  ```
  1   8   (8 * 7)   (8 * 7 * 6)   (8 * 7 * 6 * 4)  
  - , - , ------- , ----------- , --------------- ...
  1   1   (1 * 2)   (1 * 2 * 3)   (1 * 2 * 3 * 4)  
  ```

## Print Full Triangle

### Solution 1

- https://www.codingninjas.com/studio/problems/print-pascal-s-triangle_6917910
- use what we learnt in [Particular Row](#particular-row)

```java
import java.util.*;
public class Solution {
    public static int[][] pascalTriangle(int N) {
        
        int[][] ans = new int[N][];

        for (int i = 0; i < N; i++) {
            ans[i] = new int[i + 1];
            ans[i][0] = 1;
            for (int j = 1; j < i + 1; j++) {
                ans[i][j] = (int) ((ans[i][j - 1] * 1L * (i - j + 1)) / j);
            }
        }
        return ans;
    }
}
```

### Solution 2

- https://leetcode.com/problems/pascals-triangle/
- like brute force - do what the problem states

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));
        for (int i = 2; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j <= i - 2; j++) {
                row.add(result.get(i - 2).get(j) + result.get(i - 2).get(j - 1));
            }
            row.add(1);
            result.add(row);
        }
        return result;
    }
}
```
