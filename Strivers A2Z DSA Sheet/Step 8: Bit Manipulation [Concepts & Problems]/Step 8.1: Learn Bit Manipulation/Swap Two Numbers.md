# Swap Two Numbers

- https://www.codingninjas.com/studio/problems/swap-two-numbers_1380853

```java
import java.util.Arrays;

public class Solution {
    public static void swapNumber(int []a, int []b) {
        b[0] = a[0] ^ b[0];
        a[0] = a[0] ^ b[0];
        b[0] = a[0] ^ b[0];
    }
}
```
