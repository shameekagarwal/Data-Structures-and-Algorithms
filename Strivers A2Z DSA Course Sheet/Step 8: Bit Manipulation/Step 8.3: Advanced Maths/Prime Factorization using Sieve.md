# Prime Factorization using Sieve

- https://www.geeksforgeeks.org/problems/prime-factorization-using-sieve/1
- naive -  
  ```java
  for (int i = 2; i <= n; i++) {
    while (n % i == 0) {
      System.out.printf("%d, ", i);
      n /= i;
    }
  }
  ```
- note - n itself is changing - so, for e.g. for 48, i is only ever 2 and 3 - once 3 is printed, i becomes 4 but n is reduced to 1 and hence the loop stops
- however, this can still be up to O(n) for prime numbers
- optimization - run only till sqrt(n), but corner case is when n "becomes" prime - just print n in that case. now, complexity is reduced from O(n) to O(sqrt(n))
  ```java
  for (int i = 2; i * i <= n; i += 2) {
    while (n % i == 0) {
      System.out.printf("%d, ", i);
      n /= i;
    }
  }

  if (n > 1)
    System.out.printf("[%d], ", n);
  ```
- so naive was O(n) but we brought it down to O(sqrt n) by some observations
- if we have t test cases, find prime factors for each of them can go up to  O(t * sqrt(n))
- however, we can use a pre computed sieve
- each number stores its "smallest prime factor"
- recall how we were using boolean for sieve - now we store an int, which is the smallest prime number
- then, we divide using the stored number repeatedly
- sieve - 
  ```
  0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
  0  1  2  3  2  5  2  7  2  3   2  11   2  13   2   3
  ```
- so, prime factorization of 12 - 
  - sieve(12) = 2, 12 / 2 = 6
  - sieve(6) = 2, 6 / 2 = 3
  - sieve(3) = 3, 3 / 3 = 1
- time complexity? - each number can have a maximum of log<sub>2</sub>n prime factors! - if the prime factor is the smallest i.e. 2, there would be the largest number of prime factors, as the value of prime factor increases, the number of prime factors decrease
- so, we basically perform a maximum of log<sub>2</sub>n divisions
- so, for t test cases, we brought down the complexity from O(t * sqrt(n)) to O((n log(log n)) + (t * log n))
- imp below - we only mark x if `soa[x] == x`. this way, we ensure a number is marked with its smallest prime factor. this way, we ensure sorted nature of result automatically when performing our logic

```java
class Solution {

    private static final int SZ = 2_00_000;
    private static int[] soa = new int[SZ + 1];

    static {
        
        for (int i = 0; i <= SZ; i++) {
            soa[i] = i;
        }

        for (int i = 2; i * i <= SZ; i++) {
            
            if (soa[i] == i) {
                
                for (int j = i * i; j <= SZ; j += i) {

                    if (soa[j] == j) {
                        soa[j] = i;
                    }
                }
            }
        }
    }

    static void sieve() {
    }

    static List<Integer> findPrimeFactors(int N) {
        
        List<Integer> result = new ArrayList<>();
        
        while (N > 1) {
            result.add(soa[N]);
            N /= soa[N];
        }
        
        return result;
    }
}
```
