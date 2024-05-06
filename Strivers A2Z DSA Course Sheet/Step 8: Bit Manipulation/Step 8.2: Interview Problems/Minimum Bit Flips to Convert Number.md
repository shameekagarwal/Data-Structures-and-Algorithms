# Minimum Bit Flips to Convert Number

- https://leetcode.com/problems/minimum-bit-flips-to-convert-number/
- at every step, if we just compare lsb, and right shift by 1
- notice how it would also work if "one of the numbers has more leading zeroes"

```java
class Solution {
    
    public int minBitFlips(int start, int goal) {
        int flips = 0;
        while (start > 0 || goal > 0) {
            if ((start & 1) != (goal & 1)) {
                flips += 1;
            }
            start /= 2;
            goal /= 2;
        }
        return flips;
    }
}
```

## Another Solution

- xor will give the different bits
- count the different bits in xor

```java
class Solution {

    public int minBitFlips(int start, int goal) {

        int xor = start ^ goal;

        int bits = 0;

        while (xor > 0) {

            if ((xor & 1) == 1) {
                bits += 1;
            }

            xor /= 2;
        }

        return bits;
    }
}
```
