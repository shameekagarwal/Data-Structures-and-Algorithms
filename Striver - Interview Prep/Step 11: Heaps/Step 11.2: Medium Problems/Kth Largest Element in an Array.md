# Kth Largest Element in an Array

- https://leetcode.com/problems/kth-largest-element-in-an-array/
- we have a min heap which holds the k largest elements
- insert an element if - 
  - size of heap < k
  - size of heap is k and top of min heap is < incoming element
- this way, we maintain the k greatest elements from a "streaming source", and at the end, we have the k largest element, with the top being the kth largest in the entire stream
- remember - `remove`, `add`, `peek`

```java
class Solution {

    public int findKthLargest(int[] nums, int k) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (minHeap.size() < k) {
                minHeap.add(nums[i]);
            } else if (minHeap.size() == k && nums[i] > minHeap.peek()) {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
            // System.out.println(minHeap);
        }
        return minHeap.peek();
    }
}
```
