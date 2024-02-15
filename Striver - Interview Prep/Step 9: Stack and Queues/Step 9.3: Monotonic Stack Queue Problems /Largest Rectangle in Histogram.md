# Largest Rectangle in Histogram

- https://leetcode.com/problems/largest-rectangle-in-histogram/
- naive - O(n^2) - go left and right up to where possible

## Optimal

- find indices of previous smaller element (say x) and next smaller element (say y) in O(n)
- total area where height = current bar = `(y - x + 1) * heights[i]`
- time complexity - O(3 * n) - constructing pse, nse and final calculation
- space complexity -O(2 * n)
- note - for all solutions based on previous smaller and next smaller, i have noticed that people have gotten away by using only one stack - TODO: how difficult is that solution

```java
class Solution {

    public int largestRectangleArea(int[] heights) {
        
        int[] pse = getPse(heights);
        int[] nse = getNse(heights);
        // System.out.println(Arrays.toString(pse));
        // System.out.println(Arrays.toString(nse));

        int result = 0;

        for (int i = 0; i < heights.length; i++) {
            result = Math.max(result, (nse[i] - pse[i] - 1) * heights[i]);
        }
        
        return result;
    }

    private int[] getPse(int[] arr) {
        
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

    private int[] getNse(int[] arr) {
        
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

- avoid this initially unless asked
- idea is - when calculating previous smaller element, if pushing a new element, we pop everything that is bigger than itself
- so, while popping these bigger elements, we can consider these popped elements as candidates as well - 
  - their left would be the top of the stack
  - their right would be the current index we are trying to push
- this way, we only end up doing it one pass instead of 3, using one stack instead of 2
- notice how the loop runs from 0 up to n (not n - 1 like usual)
- TODO - look at other questions in this section which can make use of this optimization

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            while (!stack.isEmpty() && (i == heights.length || heights[stack.peekLast()] > heights[i])) {
                int idx = stack.removeLast();
                int start = stack.isEmpty() ? -1 : stack.peekLast();
                int width = i - start - 1;
                int currentArea = width * heights[idx];
                result = Math.max(result, currentArea);
            }
            stack.addLast(i);
        }

        return result;
    }
}
```
