# Selection Sort

- idea is - 
  - find smallest element between [0 - n-1] and swap it with element at 0
  - find smallest element between [1 - n-1] and swap it with element at 1
  - find smallest element between [2 - n-1] and swap it with element at 2
  - and so on...
- time complexity - O(n^2)

```java
public class Solution {

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            swap(arr, minIdx, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```
