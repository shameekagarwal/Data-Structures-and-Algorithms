# Fibonacci Number

- difference from other recursive patterns - multiple recursive calls are made by one call
- the first recursive call will resolve first completely, then the second recursive call, and so on
- time complexity of recursive solution - O(2^n)
- https://leetcode.com/problems/fibonacci-number/

## Recursive

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);   
    }
}
```

## Optimized

- we can even get away by just using 2-3 variables for last and second last, instead of the entire array

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        int[] memoizedFib = new int[n + 1];
        memoizedFib[0] = 0;
        memoizedFib[1] = 1;
        for (int i = 2; i <= n; i++) {
            memoizedFib[i] = memoizedFib[i - 1] + memoizedFib[i - 2];
        }
        return memoizedFib[n];
    }
}
```
