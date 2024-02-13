# String to Integer (atoi)

- constraint - 200 characters, so even long isn't enough
- note how the problem never used big integer / long / etc for optimal runtime
- https://leetcode.com/problems/string-to-integer-atoi/

```java
class Solution {
    public int myAtoi(String s) {
        
        int value = 0;
        char[] num = s.toCharArray();
        int i = 0;
        boolean isNegative = false;

        // step 1
        while (i < num.length && num[i] == ' ') {
            i += 1;
        }
        
        // step 2
        if (i < num.length && (num[i] == '-' || num[i] == '+')) {
            isNegative = (num[i] == '-');
            i += 1;
        }

        // step 3
        while (i < num.length && (num[i] >= '0' && num[i] <= '9')) {
            
            int digit = num[i] - '0';
            if (value > (Integer.MAX_VALUE - digit) / 10) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            value = (value * 10) + digit;
            i += 1;
        }
        if (isNegative) {
            value *= -1;
        }

        return value;
    }
}
```
