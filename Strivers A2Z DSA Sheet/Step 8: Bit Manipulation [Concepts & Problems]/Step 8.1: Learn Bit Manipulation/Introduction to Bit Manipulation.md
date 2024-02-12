# Introduction to Bit Manipulation

- https://www.codingninjas.com/studio/problems/bit-manipulation_8142533
- ith bit set - `1 << (i - 1)`
  ```
  0000100000
  ....i.....
  ```
- similarly, ith bit not set - `~ (1 << (i - 1))`
  ```
  1111011111
  ....i.....
  ```

```java
public class Solution {

    public static int[] bitManipulation(int num, int i) {

        int ithBit = (num >> (i - 1)) & 1;
        int setIthBit = num | (1 << (i - 1));
        int clearIthBit = num & (~ (1 << (i - 1)));
        return new int[]{ithBit, setIthBit, clearIthBit};
    }
}
```
