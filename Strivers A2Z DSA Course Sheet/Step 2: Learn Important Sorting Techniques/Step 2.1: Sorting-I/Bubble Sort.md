# Bubble Sort

- https://www.codingninjas.com/studio/problems/bubble-sort_624380
- swap adjacent elements
- first round puts last element in the correct place
- second round puts second last element in the correct place
- third round puts third last element in the correct place
- and so on
- time complexity - O(n^2)

```java
public class Solution {
    public static void bubbleSort(int[] arr, int n) {
        for (int upto = n - 1; upto > 0; upto--) {
            for (int j = 0; j < upto; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
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

# Recursive Bubble Sort

```java
public class Solution {
    public static void bubbleSort(int[] arr, int n) {
        recursiveBubbleSort(arr, arr.length - 1);
    }

    private static void recursiveBubbleSort(int[] arr, int upto) {
        if (upto <= 0) return;
        for (int i = 0; i < upto; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
        recursiveBubbleSort(arr, upto - 1);
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```
