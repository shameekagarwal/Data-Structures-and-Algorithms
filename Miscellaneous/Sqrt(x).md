# Sqrt(x)

- https://leetcode.com/problems/sqrtx/
- the only thing to take care of is overflow, which you failed 

```java
class Solution {

    public int mySqrt(int x) {

        int l = 1;
        int r = x;

        int result = 0;

        while (l <= r) {

            int m = l + ((r - l) / 2);
            long product = m * 1L * m;

            if (product == x) {
                return m;
            } else if (product < x) {
                result = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return result;
    }
}
```
