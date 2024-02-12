# Divide Two Integers

- https://leetcode.com/problems/divide-two-integers/
- so, think like this - dividend = (divisor * quotient) + remainder
- now, we can break quotient like this - 
  ```
  dividend = (divisor * (2^w + 2^x + 2^y + 2^z)) + remainder
  ```
- divisor * 2^w = divisor * (1 << w) = divisor << w
- we add (1 << w) to the quotient
- we subtract (divisor << w) from the dividend

```java
class Solution {

    public int divide(int dividend, int divisor) {
        long quotient = divide((long) dividend, (long) divisor);
        if (quotient > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (quotient < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) quotient;
    }

    private long divide(long dividend, long divisor) {

        long quotient = 0;
        boolean negative = (dividend < 0) ^ (divisor < 0);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        while (dividend >= divisor) {

            int shift = 0;

            while ((divisor << shift) <= dividend) {
                shift += 1;
            }
            dividend -= (divisor << (shift - 1));
            quotient += (1L << (shift - 1));
        }

        return negative ? (quotient * -1) : quotient;
    }
}
```
