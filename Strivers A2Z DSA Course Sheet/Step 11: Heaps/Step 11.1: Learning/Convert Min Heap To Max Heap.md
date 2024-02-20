# Convert Min Heap To Max Heap

- https://www.codingninjas.com/studio/problems/convert-min-heap-to-max-heap_1381084
- i wrote a solution where i inserted elements one by one into a new heap - thus i ignored that the given structure is a max heap, it could have as well been a stack
- online solutions says we call shift down on all nodes on - 
  - last level - 1
  - last level - 2
  - and so on till the root
- technically - shift down for every node takes log<sub>2</sub>n, and since we shift down all except nodes on last level, it might sound like complexity - O(n * log<sub>2</sub>n)
- online proves it is O(n)
- maybe idea is when i am shifting down elements on (last level - 1), the entire height of heap (log<sub>2</sub>n) is not being traveled - we only look at the level below it
- ignoring actual proof for now
- i think same logic applies to my initial solution as well - i am just constructing a heap from a given set of elements. complexity of this is not O(n * log<sub>2</sub>N), because when i insert the first element, height should not be log<sub>2</sub>N, but just 1

```java
import java.util.Arrays;

public class Solution {
    
    public static int[] MinToMaxHeap(int n, int[] arr) {

        int[] maxHeap = Arrays.copyOfRange(arr, 0, arr.length);

        for (int i = parent(arr.length - 1); i > -1; i--) {
            shiftDown(maxHeap, i);
        }

        return maxHeap;
    }

    private static void shiftDown(int[] maxHeap, int idx) {

        while (true) {

            int maxIndex = idx;
            int leftIndex = leftChild(idx);
            int rightIndex = rightChild(idx);

            if (leftIndex < maxHeap.length && maxHeap[leftIndex] > maxHeap[maxIndex]) {
                maxIndex = leftIndex;
            }

            if (rightIndex < maxHeap.length && maxHeap[rightIndex] > maxHeap[maxIndex]) {
                maxIndex = rightIndex;
            }

            if (maxIndex != idx) {
                swap(maxHeap, maxIndex, idx);
                idx = maxIndex;
            } else {
                break;
            }
        }
    }

    private static int parent(int idx) {
        return ((idx - 1) / 2);
    }

    private static int leftChild(int idx) {
        return (2 * idx) + 1;
    }

    private static int rightChild(int idx) {
        return (2 * idx) + 2;
    }

    
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
```
