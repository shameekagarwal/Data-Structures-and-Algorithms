# Minimum Operations to Exceed Threshold Value II

- https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/
- pop 2 elements from min heap and perform the operation
- continue this operation till the top of min heap is less than k

```java
class Solution {

    public int minOperations(int[] nums, int k) {
        
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        for (int i : nums) {
            minHeap.add(i * 1L);
        }
        
        int ops = 0;
        
        while (minHeap.peek() < k) {
            long min = minHeap.remove();
            long secondMin = minHeap.remove();
            minHeap.add(min * 2L + secondMin);
            ops += 1;
            // System.out.println(minHeap);
        }
        
        return ops;
    }
}
```
