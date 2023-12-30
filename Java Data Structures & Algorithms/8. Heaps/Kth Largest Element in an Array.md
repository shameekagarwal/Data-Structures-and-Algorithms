# Points

- https://leetcode.com/problems/kth-largest-element-in-an-array/
- at all times, maintain size of pq = k
- we use a min pq
- if top < incoming, remove top, replace with incoming
- always maintain pq.size() = k
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
