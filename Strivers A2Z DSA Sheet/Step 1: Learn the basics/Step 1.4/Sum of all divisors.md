# Sum of all divisors

- https://www.codingninjas.com/studio/problems/sum-of-all-divisors_8360720
- naive - O(sqrt(1) + sqrt(2) + .... sqrt(n)) - for all integers, find all divisors
- optimized - O(n) solution - a number x will be able to completely divide n / x numbers up to n
- thus it will contribute (n / x) * x to the final sum

```java
public class Solution {

    public static int sumOfAllDivisors(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += ((n / i) * i);
        }
        return result;
    }
}
```
