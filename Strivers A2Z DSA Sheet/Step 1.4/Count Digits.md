# Count Digits

- https://www.codingninjas.com/studio/problems/count-digits_8416387
- time complexity - O(log<sub>10</sub>(n))
- so, think log complexity when dividing by a number in a loop

```java
public class Solution {
    public static int countDigits(int n) {
        int temp = n;
        int result = 0;
        while (temp > 0) {
            int digit = temp % 10;
            if ((digit != 0) && (n % digit == 0)) {
                result += 1;
            }
            temp /= 10;
        }
        return result;
    }
}
```
