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

        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < k - 1; i++) {

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
        }

        int[] result = new int[n - k + 1];

        for (int i = 0; i < n - k + 1; i++) {

            if (!deque.isEmpty() && deque.peekFirst() == i - 1) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i + k - 1]) {
                deque.removeLast();
            }

            deque.addLast(i + k - 1);

            // System.out.println(deque);

            result[i] = nums[deque.peekFirst()];
        }

        return result;
    }
}
```
