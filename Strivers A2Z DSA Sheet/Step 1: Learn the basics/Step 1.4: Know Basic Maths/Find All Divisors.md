# Find All Divisors

```java
int k = (int) Math.sqrt(i);
for (int j = 1; j <= k; j++) {
    if (i % j == 0) {
        result.add(j);
        if (i / j != j) result.add(i / j);
    }
}
```
