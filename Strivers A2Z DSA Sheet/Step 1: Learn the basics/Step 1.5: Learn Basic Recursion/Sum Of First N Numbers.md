# Sum Of First N Numbers

- https://www.codingninjas.com/studio/problems/sum-of-first-n-numbers_8876068

## Parameterized Approach

```java
public class Solution {
    public static long sumFirstN(long n) {
        return sumFirstN(n, 0);
    }

    public static long sumFirstN(long n, long sum) {
        if (n == 0) return sum;
        return sumFirstN(n - 1, n + sum);
    }
}
```

## Functional Approach

- break into smaller problems, functional style

```java
public class Solution {
    public static long sumFirstN(long n) {
        if (n <= 1) return n;
        return n + sumFirstN(n - 1);
    }
}
```

## Optimized

- important - notice how we avoid overflow when multiplying `n * (n + 1)`

```java
public class Solution {
    public static long sumFirstN(long n) {
        if (n % 2 == 0) return (n / 2) * (n + 1);
        else return n * ((n + 1) / 2);
    }
}
```

