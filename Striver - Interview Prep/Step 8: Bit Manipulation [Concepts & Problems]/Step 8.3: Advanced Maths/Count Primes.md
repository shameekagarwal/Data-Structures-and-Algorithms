# Count Primes

- https://leetcode.com/problems/count-primes/
- all primes strictly less than n - calculate sieve up to n - 1 and count all the primes

```java
class Solution {
    
    public int countPrimes(int n) {

        if (n <= 2) return 0;

        boolean[] sieve = new boolean[n];
        sieve[0] = true;
        sieve[1] = true;

        for (int i = 2; i * i < n; i++) {
            if (!sieve[i]) {
                for (int j = i * i; j < n; j += i) {
                    sieve[j] = true;
                }
            }
        }

        int result = 0;
        for (int i = 2; i < n; i++) {
            if (!sieve[i]) result += 1;
        }

        return result;
    }
}
```
