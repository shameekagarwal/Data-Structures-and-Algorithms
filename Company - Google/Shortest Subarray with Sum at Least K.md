# Shortest Subarray with Sum at Least K

- https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
- observation 1 - 
  - if `x1 < x2` and `Prefix[x1] > Prefix[x2]`
  - no point considering x1 i.e. we can discard x1
  - why - because we can rewrite it as `Prefix[Y] - Prefix[X1] < Prefix[Y] - Prefix[X2]`
  - this means we have a better chance using x2-y than x1-y
  - and x2-y is anyway preferable since we are looking for smallest subarray
  - logic behind all this - it basically means sum between x1 to x2 is -ve - so why bother including it
- observation 2 - 
  - for current element ending at i
  - we want best possible answer
  - assume we find x-i works
  - no point considering x for future i's
  - because x-i is already possible, and x-future_i will just make the potential answer longer, and we want the smallest subarray
  - so, once x-i works, discard x for all future i's

```java
class Solution {

    public int shortestSubarray(int[] nums, int k) {
        
        long[] prefix = new long[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int result = -1;

        for (int i = 0; i < nums.length; i++) {

            while (!deque.isEmpty() && prefix[i] - prefix[deque.peekFirst()] >= k) {
                int len = i - deque.removeFirst();
                result = result == -1 ? len : Math.min(len, result);
            }

            if (prefix[i] >= k) {
                result = result == -1 ? i + 1 : Math.min(i + 1, result);
            }

            while (!deque.isEmpty() && prefix[deque.peekLast()] >= prefix[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
        }

        return result;
    }
}
```
