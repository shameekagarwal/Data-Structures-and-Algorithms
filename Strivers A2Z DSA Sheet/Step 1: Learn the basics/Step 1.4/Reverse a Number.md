# Reverse a Number

- https://leetcode.com/problems/reverse-integer/

```java
class Solution {
    public int reverse(int x) {
        long result = 0;
        boolean isNegative = x < 0;
        x = Math.abs(x);
        while (x > 0) {
            result = (result * 10) + (x % 10);
            x /= 10;
        }
        if (isNegative) result = result * (-1);
        return (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) ? 
                0 : 
                (int) result;
    }
}
```
