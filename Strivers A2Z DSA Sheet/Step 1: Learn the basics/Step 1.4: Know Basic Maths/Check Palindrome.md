# Palindrome Number

- https://leetcode.com/problems/palindrome-number/
- use long to avoid overflow - reverse of an int can be a long

```java
class Solution {
    public boolean isPalindrome(int x) {
        long y = 0;
        long temp = x;
        while (temp > 0) {
            y = (y * 10) + (temp % 10);
            temp /= 10;
        }
        return x == y;
    }
}
```
