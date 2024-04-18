# Harshad Number

- https://leetcode.com/problems/harshad-number/

```java
class Solution {

    public int sumOfTheDigitsOfHarshadNumber(int x) {

        int sumOfDigits = getSumOfDigits(x);
        
        if (x % sumOfDigits == 0) {
            return sumOfDigits;
        }
        
        return -1;
    }
    
    private int getSumOfDigits(int x) {
        
        int sumOfDigits = 0;
        
        while (x > 0) {
            sumOfDigits += (x % 10);
            x /= 10;
        }
        
        return sumOfDigits;
    }
}
```
