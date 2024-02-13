# Advanced Maths

## Check Prime

- 2 is the only even prime number
- every prime number except 2 and 3 can be expressed as 6n + 1 or 6n - 1, e.g. 11 - (6 * 2) - 1
- goldbach's conjecture - any even number (except 2) can be expressed as a sum of two prime numbers. e.g. 16 = 13 + 3
- only two consecutive primes - 2 and 3
- wilsons theorem - (p - 1)! % p = (p - 1), where p is prime - e.g. 4! % 5 = 4
- prime - only two factors - 1 and itself
- [Check Prime](/Strivers%20A2Z%20DSA%20Sheet/Step%201:%20Learn%20the%20basics/Step%201.4:%20Know%20Basic%20Maths/Check%20Prime.md), how complexity goes from O(N) to sqrt(N)

## Sieve of Eratosthenes

- e.g. we have t test cases, where we need to print for each test case if n is prime or not
- using the check prime method above, complexity - O(T * sqrt(N))
- solution - pre compute sieve of eratosthenes
- all multiples of anything that is a prime in the sieve cannot be a prime
  - mark multiples of 2 as not prime
  - mark multiples of 3 as not prime
  - skip 4 - multiples of 4 were already a multiple of 2, so it is already covered
  - mark multiples of 5 as not prime
- make all elements of array as true, then mark them as false one by one
  ```java
  int upto = 100;
  boolean[] isNotPrime = new boolean[upto + 1];
  isNotPrime[0] = true;
  isNotPrime[1] = true;

  for (int i = 2; i <= upto; i++) {
    if (!isNotPrime[i]) {
      for (int j = 2 * i; j <= upto; j += i) {
        isNotPrime[j] = true;
      }
    }
  }
  ```
- optimizations -
  - we filled 4, 6, 8, 10... for 2
  - we filled 6, 9, 12, 15... for 3 - but 6 would have been filled by 2 already, we can start from 9
  - we filled 5, 10, 15, 20.... for 5 - but 5, 10, 15, 20 would have been filled by 5 already, we can start from 25
- so, overall, we can start from i * i
- by the same logic, no need of running the outer loops beyond sqrt(N)
- so, the algorithm can be rewritten as follows - (look at the changes in both the loop's limits)
  ```java
  int upto = 100;
  boolean[] isNotPrime = new boolean[upto + 1];
  isNotPrime[0] = true;
  isNotPrime[1] = true;

  for (int i = 2; i * i <= upto; i++) {
    if (!isNotPrime[i]) {
      for (int j = i * i; j <= upto; j += i) {
        isNotPrime[j] = true;
      }
    }
  }
  ```
- final time complexity of sieve of eratosthenes - O(n * log(log(n))

## Prime Factorization

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

## Prime Factorization + Sieve of Eratosthenes

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
