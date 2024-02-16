# Min Heap Implementation

- https://www.codingninjas.com/studio/problems/min-heap-implementation_5480527
- doubt - is the implementation of `deleteElement` correct? i just call both `shiftUp` and `shiftDown`
- remember this entire implementation as is

```java
public class Solution {

    static class MinHeap {

        private int[] arr;
        private int size;

        MinHeap(int capacity) {
            arr = new int[capacity];
            size = 0;
        }

        int extractMinElement() {
            
            if (size == 0) return -1;

            int minElement = arr[0];
            arr[0] = arr[size - 1];
            size -= 1;
            shiftDown(0);
            return minElement;
        }

        void deleteElement(int ind) {

            if (ind < 0 || ind >= size) return;

            int deletedElement = arr[ind];
            arr[ind] = arr[size - 1];
            size -= 1;
            shiftUp(ind);
            shiftDown(ind);
        }

        void insert(int val) {

            if (size == arr.length) return;

            arr[size] = val;
            shiftUp(size);
            size += 1;
        }

        void shiftUp(int index) {

            while (true) {

                int parentIndex = parent(index);

                if (index > 0 && arr[parentIndex] > arr[index]) {
                    swap(parentIndex, index);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        }

        void shiftDown(int index) {

            while (true) {

                int minIndex = index;
                int leftIndex = leftChild(index);
                int rightIndex = rightChild(index);

                if (leftIndex < size && arr[leftIndex] < arr[minIndex]) {
                    minIndex = leftIndex;
                }

                if (rightIndex < size && arr[rightIndex] < arr[minIndex]) {
                    minIndex = rightIndex;
                }

                if (minIndex != index) {
                    swap(index, minIndex);
                    index = minIndex;
                } else {
                    break;
                }
            }
        }

        int leftChild(int index) {
            return 2 * index + 1;
        }

        int rightChild(int index) {
            return 2 * index + 2;
        }

        int parent(int index) {
            return (index - 1) / 2;
        }

        void swap(int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
```
