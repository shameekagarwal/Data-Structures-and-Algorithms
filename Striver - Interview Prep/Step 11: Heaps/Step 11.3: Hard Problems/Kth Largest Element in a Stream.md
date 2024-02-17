# Kth Largest Element in a Stream

- https://leetcode.com/problems/kth-largest-element-in-a-stream/
- almost same as [Kth Largest Element in an Array](../Step%2011.2:%20Medium%20Problems/Kth%20Largest%20Element%20in%20an%20Array.md)
- common mistake - do not add all elements to priority queue in constructor, add all of them one by one by reusing the add function

```java
class KthLargest {

    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (minHeap.size() == k && minHeap.peek() < val) {
            minHeap.remove();
            minHeap.add(val);
        } else if (minHeap.size() < k) {
            minHeap.add(val);
        }
        return minHeap.peek();
    }
}
```
