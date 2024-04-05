# Sort K Sorted Array

- https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1
- brute - shouts selection sort to me - 
  - normal insertion sort will go 0 to N - 1 for picking smallest element. this will go from 0 to K
  - normal insertion sort will go 1 to N - 2 for picking second smallest element. this will go from 1 to K + 1
  - and so on...
- so, brute time complexity - O(N * k)
- optimal - insert k + 1 elements into the min heap - note k + 1 and not k because "shift" is by k
- then, peek top for first element, and add k+1th element min heap
- then, peek top for second element, and add k+2th element to min heap
- and so on...
- time complexity - O(k) (building heap of k) + O((N - k) * log<sub>2</sub>)

```java
class Solution {

    ArrayList<Integer> nearlySorted(int arr[], int n, int k) {
        
        k = Math.min(n - 1, k);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }
        
        for (int i = k; i < n; i++) {
            minHeap.add(arr[i]);
            result.add(minHeap.poll());
        }
        
        for (int i = n - k; i < n; i++) {
            result.add(minHeap.poll());
        }
        
        return result;
    }
}
```
