# Largest Element in an Array

- array - all elements stored at contiguous memory locations
- https://www.codingninjas.com/studio/problems/largest-element-in-the-array-largest-element-in-the-array_5026279

```java
import java.util.* ;
import java.io.*; 

public class Solution {

    static int largestElement(int[] arr, int n) {
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            maxElement = Math.max(maxElement, arr[i]);
        }
        return maxElement;
    }
}
```
