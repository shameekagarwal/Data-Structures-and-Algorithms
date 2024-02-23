# Power of Two

- https://leetcode.com/problems/power-of-two/
- 32 bits are represented as - (-2^31 to 2^31 - 1) in java
- note - java has no concept of unsigned
- so, for power of two, we only need to check up to 1 << 30
- because 1 << 31 does not fit inside an integer
- optimization - if calculated number exceeds n, we break out of loop
- n can be negative according to question, but nothing raised to 2 can give us a negative number
- since we only check against positive, we are good

```java
class Solution {
    
    public boolean isPowerOfTwo(int n) {
        for (int i = 0; i < 31; i++) {
            int twoRaisedToI = 1 << i;
            if (n == twoRaisedToI) return true;
            else if (n < twoRaisedToI) return false;
        }
        return false;
    }
}
```

## Recursive

- edge case - anything <= 0

```java
class Solution {
    
    public boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        if (n <= 0) return false;
        if (n % 2 != 0) return false;
        return isPowerOfTwo(n / 2);
    }
}
```
