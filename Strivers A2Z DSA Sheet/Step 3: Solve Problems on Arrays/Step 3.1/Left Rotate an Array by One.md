# Left Rotate an Array by One

- https://www.codingninjas.com/studio/problems/left-rotate-an-array-by-one_5026278

```java
import java.util.* ;
import java.io.*; 

public class Solution {

    static int[] rotateArray(int[] arr, int n) {
        int output[] = new int[arr.length];
        int firstElement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            output[i - 1] = arr[i];
        }
        output[arr.length - 1] = firstElement;
        return output;
    }
}
```
