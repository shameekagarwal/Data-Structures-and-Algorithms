# Pow(x, n)

- https://leetcode.com/problems/powx-n/
- brute force - iterate from 1 to n and keep multiplying. complexity - O(n)
- optimal - log2(n). pow(x, n) = 
  - x * pow(x, n - 1) if n is odd
  - pow(x * x, n / 2) if n is even
- if n is negative, we make it positive and return 1 / result
- edge case when making n positive from negative - `Integer.MIN_VALUE` would not fit inside int when making it positive, so use long for n's copy

```java
class Solution {

    public double myPow(double _x, int _n) {
        
        long n = _n < 0 ? _n * -1L : _n;
        double result = 1;
        double x = _x;

        while (n > 0) {
            if (n % 2 == 1) {
                n -= 1;
                result = result * x;
            } else {
                n /= 2;
                x *= x;
            }
        }

        return _n < 0 ? 1 / result : result;
    }
}
```

## Recursive

```java
class Solution {

    public double myPow(double x, int n) {
        return n < 0 ? 1 / _myPow(x, n * -1L) : _myPow(x, n);
    }

    private double _myPow(double x, long n) {
        if (n == 0) return 1;
        if (n % 2 == 1) {
            return x * _myPow(x, n - 1);
        } else {
            return _myPow(x * x, n / 2);
        }
    }
}
```
