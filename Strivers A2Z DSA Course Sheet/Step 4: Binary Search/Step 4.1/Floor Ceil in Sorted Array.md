# Floor/Ceil in Sorted Array

- https://www.codingninjas.com/studio/problems/ceiling-in-a-sorted-array_1825401
- floor - biggest `a[x]` such that `a[x] <= target` - check <= first
- ceil - smallest `a[x]` such that `a[x] >= target` - same as lower bound - check >= first

```java
import java.util.* ;
import java.io.*; 

public class Solution {
  public static int[] getFloorAndCeil(int[] a, int n, int x) {
    int floor = findFloor(a, x);
    int ceiling = findCeiling(a, x);
    return new int[]{floor, ceiling};
  }

  private static int findFloor(int[] a, int x) {

    int low = 0;
    int high = a.length - 1;
    int floor = -1;

    while (low <= high) {
      int mid = getMid(low, high);
      if (a[mid] <= x) {
        floor = a[mid];
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return floor;
  }

  private static int findCeiling(int[] a, int x) {

    int low = 0;
    int high = a.length - 1;
    int ceiling = -1;

    while (low <= high) {
      int mid = getMid(low, high);
      if (a[mid] >= x) {
        ceiling = a[mid];
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    
    return ceiling;
  }

  private static int getMid(int low, int high) {
    return low + ((high - low) / 2);
  }
}
```
