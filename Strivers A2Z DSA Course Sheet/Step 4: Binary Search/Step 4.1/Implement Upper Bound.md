# Implement Upper Bound

- https://www.codingninjas.com/studio/problems/implement-upper-bound_8165383
- upper bound - just change the condition to `a[x] > target`
- recall - lower bound was `a[x] >= target`, so we just get rid of the `=` in upper bound

```java
public class Solution {
    public static int upperBound(int []arr, int x, int n){
        
        int low = 0;
        int high = arr.length - 1;
        int result = arr.length;

        while (low <= high) {
            int mid = getMid(low, high);
            if (arr[mid] > x) {
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
