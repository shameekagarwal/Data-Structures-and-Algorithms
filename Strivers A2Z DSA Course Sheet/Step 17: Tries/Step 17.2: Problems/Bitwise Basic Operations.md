# Bitwise Basic Operations

- https://www.naukri.com/code360/problems/bitwise-basic-operations_8382552

```java
public class Solution {

    public static int getXOR(int a, int b) {
        return (a ^ b);
    }

    public static int getBit(int c, int d) {
        return ((d >> c) & 1);
    }

    public static int setBit(int e, int f) {
        return (f | (1 << e));
    }
}
```
