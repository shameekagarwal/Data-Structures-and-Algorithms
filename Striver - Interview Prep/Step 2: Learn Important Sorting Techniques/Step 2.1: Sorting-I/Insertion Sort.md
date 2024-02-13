# Insertion Sort

- https://www.codingninjas.com/studio/problems/insertion-sort_624381
- every iteration, the element at ith position is put in the right spot, such that the array 0 - i is sorted, by swapping the element
- first round puts element at index 0 at the correct place, such that array 0 - 0 is sorted
- second round puts element at index 1 at the correct place, such that array 0 - 1 is sorted
- third round puts element at index 2 at the correct place, such that array 0 - 2 is sorted

```java
public class Solution {
    public static void insertionSort(int[] arr, int size) {
        for (int from = 1; from < arr.length; from++) {
            for (int j = from; j > 0; j--) {
                if (arr[j] > arr[j - 1]) break;
                swap(arr, j, j - 1);
            }
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```

# Recursive Insertion Sort

```java
public class Solution {
    public static void insertionSort(int[] arr, int size) {
        recursiveInsertionSort(arr, 0);
    }

    private static void recursiveInsertionSort(int[] arr, int from) {
        if (from >= arr.length) return;
        for (int i = from; i > 0; i--) {
            if (arr[i] > arr[i - 1]) break;
            swap(arr, i, i - 1);
        }
        recursiveInsertionSort(arr, from + 1);
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```
