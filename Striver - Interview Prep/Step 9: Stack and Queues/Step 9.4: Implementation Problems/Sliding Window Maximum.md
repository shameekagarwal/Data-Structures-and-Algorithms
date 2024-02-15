# Sliding Window Maximum

- https://leetcode.com/problems/sliding-window-maximum/
- naive - O(n*k) - i -> 0...n-1, j -> i...i+k-1 
- we use a deque
- we add k elements to it from the end
- when adding an element, pop from the deque from end - assume we have x and y such that x > y and `arr[x] > arr[y]`. there would be no use of y in any windows where x is present as well - because x is greater than y and `arr[x]` is also > `arr[y]`
- assume this example - array has 6 elements, window size is 3
  ```
  array index -     0 1 2 3 4 5 6
  end of window i -     0 1 2 3 4
  ```
- so, first add `i` at end, but before that remove all elements till `arr[deque.top()] <= arr[i]`
- k - 1 onwards, we start evaluating the result because a full window is complete
- finally, once we start evaluating windows, we start removing elements from the front of deque as well if they cannot be part of the next window

```java
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> window = new ArrayDeque<>();

        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {

            while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]) {
                window.removeLast();
            }
            window.addLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[window.peekFirst()];
                if (window.peekFirst() == i - k + 1) {
                    window.removeFirst();
                }
            }
        }

        return result;
    }
}
```
