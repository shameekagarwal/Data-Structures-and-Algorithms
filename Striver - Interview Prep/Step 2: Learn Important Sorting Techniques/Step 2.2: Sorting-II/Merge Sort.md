# Merge Sort

- divide then merge
- merge - 
  - one pointer for each of the half
  - compare them
  - add the smaller pointer to the sorted list
  - move this pointer forward
- time complexity - at every step, array gets divided into two parts - log n steps, but merge is also there. so total - O(n * log n)
- remember the additional temporary array that is needed at every merge step
- https://www.codingninjas.com/studio/problems/merge-sort_5846

```java
import java.util.List;
import java.util.ArrayList;

public class Solution {

    public static void mergeSort(int[] arr, int l, int r) {
        
        if (l >= r) return;
        
        int leftStart = l;
        int leftEnd = (l + r) / 2;
        int rightStart = leftEnd + 1;
        int rightEnd = r;
        
        mergeSort(arr, leftStart, leftEnd);
        mergeSort(arr, rightStart, rightEnd);
        
        List<Integer> sorted = new ArrayList<>();
        int pointerLeft = leftStart;
        int pointerRight = rightStart;

        while (pointerLeft <= leftEnd && pointerRight <= rightEnd) {
            if (arr[pointerLeft] < arr[pointerRight]) {
                sorted.add(arr[pointerLeft]);
                pointerLeft += 1;
            } else {
                sorted.add(arr[pointerRight]);
                pointerRight += 1;
            }
        }

        while (pointerLeft <= leftEnd) {
            sorted.add(arr[pointerLeft]);
            pointerLeft += 1;
        }
        while (pointerRight <= rightEnd) {
            sorted.add(arr[pointerRight]);
            pointerRight += 1;
        }

        for (int i = l; i <= r; i++) {
            arr[i] = sorted.get(i - l);
        }
    }
}
```
