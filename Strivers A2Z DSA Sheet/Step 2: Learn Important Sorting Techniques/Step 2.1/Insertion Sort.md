# Insertion Sort

- every iteration, the element at ith position is put in the right spot, such that the array 0 - i is sorted, by swapping the element
- first round puts element at index 0 at the correct place, such that array 0 - 0 is sorted
- second round puts element at index 1 at the correct place, such that array 0 - 1 is sorted
- third round puts element at index 2 at the correct place, such that array 0 - 2 is sorted

```java
public class Solution {
    public static void insertionSort(int[] arr, int size) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
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

    private static void recursiveInsertionSort(int[] arr, int upto) {
        if (upto >= arr.length) return;
        for (int i = upto; i > 0; i--) {
            if (arr[i] > arr[i - 1]) break;
            swap(arr, i, i - 1);
        }
        recursiveInsertionSort(arr, upto + 1);
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```
