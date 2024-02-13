# Count Good numbers

- https://leetcode.com/problems/count-good-numbers/
- primes - 2 3 5 7
- even - 0 2 4 6 8
- so, count for length 1 - 5, length 2 - 5 * 4, length 3 - 5 * 4 * 5 and so on
- though the intention was recursion, didn't use it, can use it for power if needed, already seen [here](./Pow(x,%20n).md)

```java
class Solution {

    private static final int MOD = 1000000007;

    public int countGoodNumbers(long n) {
        long evenCount = n - n / 2;
        long primeCount = n / 2;
        return (int) ((pow(5, evenCount) * pow(4, primeCount)) % MOD);
    }

    private long pow(long x, long n) {
        
        long result = 1;

        while (n > 0) {
            if (n % 2 == 1) {
                result = (result * x) % MOD;
                n -= 1;
            } else {
                n /= 2;
                x = (x * x) % MOD;
            }
        }

        return result;
    }
}
```
