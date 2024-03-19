# Find the Sum of Encrypted Integers

- https://leetcode.com/problems/find-the-sum-of-encrypted-integers/

```java
class Solution {

    public int sumOfEncryptedInt(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result += calculate(num);
        }
        return result;
    }
    
    private int calculate(int num) {
        
        int largestDigit = 0;
        int countOfDigits = 0;
        
        while (num > 0) {
            largestDigit = Math.max(num % 10, largestDigit);
            countOfDigits += 1;
            num /= 10;
        }
        
        int value = 0;
        for (int i = 0; i < countOfDigits; i++) {
            value = (value * 10) + largestDigit;
        }
        
        return value;
    }
}
```
