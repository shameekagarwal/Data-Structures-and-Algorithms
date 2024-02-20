# Number of occurrence

- https://www.codingninjas.com/studio/problems/occurrence-of-x-in-a-sorted-array_630456
- exact same as [First and Last Position in Sorted Array](./First%20and%20Last%20Position%20in%20Sorted%20Array.md)
- just return the first and last positions of the element

```java
public class Solution {

    public static int count(int arr[], int n, int x) {

        int firstOccurrence = findFirstOccurrence(arr, x);
        if (firstOccurrence == -1) return 0;
        int lastOccurrence = findLastOccurrence(arr, x);
        return lastOccurrence - firstOccurrence + 1;
    }

    private static int findFirstOccurrence(int arr[], int target) {

        int low = 0;
        int high = arr.length - 1;
        int firstOccurrence = -1;

        while (low <= high) {

            int mid = getMid(low, high);

            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                firstOccurrence = mid;
                high = mid - 1;
            }
        }

        return firstOccurrence;
    }
    
    private static int findLastOccurrence(int arr[], int target) {

        int low = 0;
        int high = arr.length - 1;
        int lastOccurrence = -1;

        while (low <= high) {

            int mid = getMid(low, high);

            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                lastOccurrence = mid;
                low = mid + 1;
            }
        }

        return lastOccurrence;
    }

    private static int getMid(int low, int high) {
        return low + ((high - low) / 2);
    }
}
```
