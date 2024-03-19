# String to Integer (atoi)

- constraint - 200 characters, so even long isn't enough
- note how the problem never used big integer / long / etc for optimal runtime
- https://leetcode.com/problems/string-to-integer-atoi/
- dry run to prove the if condition - e.g. 123456 is the max int max value
- if we have string "123457"
- the last digit subtraction / 10 for int_max will make it 12344 - which would be lesser than the first 5 digits of 123457 i.e. 12345

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
