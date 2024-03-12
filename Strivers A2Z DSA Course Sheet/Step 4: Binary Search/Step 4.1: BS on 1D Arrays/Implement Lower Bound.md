# Implement Lower Bound

- smallest x such that `a[x] >= target`
- if nothing is found, the answer will be our "hypothetical index" of array's length
- apparently, all this does not have stl in java unlike cpp
- time complexity - O(log2n)
- https://www.codingninjas.com/studio/problems/lower-bound_8165382

```java
public class Solution {
    public static int lowerBound(int []arr, int n, int x) {
        
        int low = 0;
        int high = arr.length - 1;
        int result = arr.length;

        while (low <= high) {
            int mid = getMid(low, high);
            if (arr[mid] >= x) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private static int getMid(int low, int high) {
        return low + ((high - low) / 2);
    }
}
```
