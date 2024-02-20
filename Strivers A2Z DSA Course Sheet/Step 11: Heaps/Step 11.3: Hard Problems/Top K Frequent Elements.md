# Top K Frequent Elements

- https://leetcode.com/problems/top-k-frequent-elements/
- naive - O(N * logN)
  - step 1 - calculate frequency
  - step 2 - sort based on frequency
- optimal - construct a frequency lookup and maintain a pq of size k based on the same logic as [Kth Largest Element in an Array](../Step%2011.2:%20Medium%20Problems/Kth%20Largest%20Element%20in%20an%20Array.md)
- time complexity three parts - 
  - building frequency hash map - O(N)
  - building heap - O((N-K) * logK + K) - why?
    - for first K elements, say it takes (K * logK) - actually i guess it should take O(K) because building a heap of size k takes O(K) time, but anyway
    - for next K elements, size of heap does not exceed K - so, it stays at O((N-K) * logK)
  - for popping K elements - again O(K * logK) but should be even lesser
- so, we kind of stay under O(N*logK)

```java
class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> frequencyLookup = new HashMap<>();
        for (int num : nums) {
            frequencyLookup.put(num, frequencyLookup.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<HeapNode> frequencyHeap = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : frequencyLookup.entrySet()) {
            if (frequencyHeap.size() == k && frequencyHeap.peek().frequency < entry.getValue()) {
                frequencyHeap.remove();
                frequencyHeap.add(new HeapNode(entry.getKey(), entry.getValue()));
            } else if (frequencyHeap.size() < k) {
                frequencyHeap.add(new HeapNode(entry.getKey(), entry.getValue()));
            }
            // System.out.printf("processing: %s, heap: %s\n", entry, frequencyHeap);
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = frequencyHeap.remove().element;
        }
        return result;
    }

    static class HeapNode implements Comparable<HeapNode> {

        int element;
        int frequency;

        HeapNode(int element, int frequency) {
            this.element = element;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(HeapNode heapNode) {
            return frequency - heapNode.frequency;
        }

        @Override
        public String toString() {
            return "HeapNode(element: " + element + ", frequency: " + frequency + ")";
        }
    }
}
```
