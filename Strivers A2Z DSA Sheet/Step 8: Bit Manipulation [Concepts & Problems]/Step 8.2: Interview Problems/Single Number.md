# Single Number

- https://leetcode.com/problems/single-number/
- xor of all numbers
- note property of xor - xor can never be larger than the largest of the numbers on which xor is being performed

```java
class Solution {
    
    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int i : nums) {
            xor ^= i;
        }
        return xor;
    }
}
```
