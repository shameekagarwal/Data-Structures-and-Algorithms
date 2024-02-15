# Maximal Rectangle

- https://leetcode.com/problems/maximal-rectangle/
- solution - 
  - step 1 - convert each row to a histogram - `histogram[j] = mat[i][j] == '0' ? 0 : histogram[j] + 1`
  - step 2 - solve it using [Largest Rectangle in Histogram](./Largest%20Rectangle%20in%20Histogram.md)
- when we iterate through the rows, we try calculating max area of all rectangles "ending at row i"

## Optimal

```java
class Solution {

    public int maximalRectangle(char[][] matrix) {

        int[] currentHistogram = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            currentHistogram[i] = matrix[0][i] - '0';
        }

        int result = calculateMaxArea(currentHistogram);

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                currentHistogram[j] = (matrix[i][j] == '0') ? 0 : (currentHistogram[j] + 1);
            }
            result = Math.max(result, calculateMaxArea(currentHistogram));
        }

        return result;
    }

    private int calculateMaxArea(int[] histogram) {

        int[] pse = calculatePse(histogram);
        int[] nse = calculateNse(histogram);

        // System.out.println("array: " + Arrays.toString(histogram));
        // System.out.println("pse: " + Arrays.toString(pse));
        // System.out.println("nse: " + Arrays.toString(nse));

        int result = 0;
        for (int i = 0; i < histogram.length; i++) {
            int width = nse[i] - pse[i] - 1;
            int area = width * histogram[i];
            result = Math.max(result, area);
        }
        return result;
    }

    private int[] calculatePse(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] pse = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peekLast()] >= arr[i]) {
                stack.removeLast();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(i);
        }
        return pse;
    }

    private int[] calculateNse(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nse = new int[arr.length];
        for (int i = arr.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && arr[stack.peekLast()] >= arr[i]) {
                stack.removeLast();
            }
            nse[i] = stack.isEmpty() ? arr.length : stack.peekLast();
            stack.addLast(i);
        }
        return nse;
    }
}

```

## More Optimized

- again based on [Largest Rectangle in Histogram](./Largest%20Rectangle%20in%20Histogram.md), just change implementation of `calculateMaxArea`

```java
private int calculateMaxArea(int[] histogram) {
    
    Deque<Integer> stack = new ArrayDeque<>();
    int result = 0;
    
    for (int i = 0; i <= histogram.length; i++) {
        while (!stack.isEmpty() && ((i == histogram.length) || (histogram[stack.peekLast()] >= histogram[i]))) {
            int height = histogram[stack.removeLast()];
            int start = stack.isEmpty() ? -1 : stack.peekLast();
            int width = i - start - 1;
            result = Math.max(result, width * height);
        }
        stack.addLast(i);
    }

    return result;
}
```
