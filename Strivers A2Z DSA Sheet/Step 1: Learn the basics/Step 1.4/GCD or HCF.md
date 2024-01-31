# GCD or HCF

- https://www.codingninjas.com/studio/problems/hcf-and-lcm_840448
- naive solution - O(sqrt(n))
  - check all divisors of the min of the two
  - verify if both the numbers are completely divisible by it
- optimized - O(log<sub>something</sub>(n))))
  - euclidean algorithm - gcd(a, b) = gcd(a % b, b), where a > b
- my layman thought if asked intuition of euclidean algorithm -
  - let a > b
  - let a = (p * b) + r
  - we want a % gcd = 0 and b % gcd = 0
  - a % gcd will be 0 only if r % gcd = 0 (because b % gcd is already 0)
  - so, f_gcd(a, b) = f_gcd(b, r)
  - r is basically a % b

# Naive

```java
public class Solution {
    public static int calcGCD(int n, int m){
        int result = 0;
        int minOfTheTwo = Math.min(m, n);
        for (int i = 1; i <= Math.sqrt(minOfTheTwo); i++) {
            int j = minOfTheTwo / i;
            if ((m % i == 0) && (n % i == 0)) {
                result = Math.max(i, result);
            }
            if ((m % j == 0) && (n % j == 0)) {
                result = Math.max(j, result);
            }
        }
        return result;
    }
}
```

# Optimized

```java
public class Solution {
    public static int calcGCD(int n, int m) {
        if (m < n) {
            int temp = n;
            n = m;
            m = temp;
        }
        while (n != 0) {
            int remainder = m % n;
            m = n;
            n = remainder;
        }
        return m;
    }
}
```
