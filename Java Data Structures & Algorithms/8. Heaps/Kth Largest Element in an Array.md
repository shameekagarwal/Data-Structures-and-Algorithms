# Points

- https://leetcode.com/problems/kth-largest-element-in-an-array/
- we use a min pq
- always maintain pq.size() = k
- if top < incoming, remove top, replace with incoming
- finally, pq has k of the largest elements
- since it is a min pq, top is kth largest

# Solution 1

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if (pq.size() == k + 1) pq.poll();
        }
        return pq.peek();
    }
}
```

# Solution 2

- TODO: quick select
