# Check whether K-th bit is set or not

- https://www.codingninjas.com/studio/problems/check-whether-k-th-bit-is-set-or-not_5026446

```java
public class Solution {
    static boolean isKthBitSet(int n, int k) {
        return ((n >> (k - 1)) & 1) == 1 ? true : false;
    }
}
```
