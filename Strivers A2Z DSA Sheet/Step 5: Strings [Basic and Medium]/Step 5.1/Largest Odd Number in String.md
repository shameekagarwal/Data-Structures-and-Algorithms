# Largest Odd Number in String

- https://leetcode.com/problems/largest-odd-number-in-string/
- iterate from end - we are looking for odd numbers ending at i. if i is odd, largest odd would be substring from 0 to i

```java
class Solution {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i > -1; i--) {
            if ((num.charAt(i) - '0') % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
```
