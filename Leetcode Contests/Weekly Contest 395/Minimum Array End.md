# Minimum Array End

- https://leetcode.com/problems/minimum-array-end/
- observation 1 - array has to start with at least x - no number smaller than x can have all the same bits set as in x
- now, assume x is of this form - 
  ```
  1 1 1 0 0 0 0 0 1 1 1 0 1 1 0 1 1 1 0 1 1 1 0 1 1 0 0 1
  ```
- for next number, we have to set last 0 as 1
  ```
  1 1 1 0 0 0 0 0 1 1 1 0 1 1 0 1 1 1 0 1 1 1 0 1 1 0 "1" 1
  ```
- for next number, we have to set last two 0s as 10
  ```
  1 1 1 0 0 0 0 0 1 1 1 0 1 1 0 1 1 1 0 1 1 1 0 1 1 "1" 0 1
  ```
- for next number, we have to set last three 0s as 11
  ```
  1 1 1 0 0 0 0 0 1 1 1 0 1 1 0 1 1 1 0 1 1 1 0 1 1 "1" "1" 1
  ```
- and this process continues till n - 1 - lets call this bits
- as soon as we see current bit is 0, set it the same value as lsb of bits, and right shift bits
- corner case to remember (handled below using `|| bits > 0`) - assume x is 7 and n is 3. now, we do not have any or "enough" 0s in between

```java
class Solution {

    public long minEnd(int n, int x) {

        int bits = n - 1;
        
        long result = x;
        
        long pos = 1;
        
        while (x > 0 || bits > 0) {

            if (x % 2 == 0) {
                if (bits % 2 == 1) {
                    result |= pos;
                }
                bits /= 2;
            }

            x /= 2;
            pos *= 2;
        }
        
        return result;
    }
}
```
