# Does array represent Heap

- https://www.geeksforgeeks.org/problems/does-array-represent-heap4345/
- check one by one from parent to all its children

```java
class Solution {
    
    public boolean countSub(long arr[], long n) {
        return isHeap(arr, 0);
    }

    private boolean isHeap(long[] heap, int index) {

        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        if (leftIndex < heap.length) {
            if (heap[leftIndex] > heap[index]) return false;
            if (!isHeap(heap, leftIndex)) return false;
        }

        if (rightIndex < heap.length) {
            if (heap[rightIndex] > heap[index]) return false;
            if (!isHeap(heap, rightIndex)) return false;
        }

        return true;
    }
    
    private int leftChild(int index) {
        return (2 * index) + 1;
    }
    
    private int rightChild(int index) {
        return (2 * index) + 2;
    }
}
```
