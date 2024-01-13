# Factorial of N numbers

- https://www.codingninjas.com/studio/problems/factorial-numbers-not-greater-than-n_8365435

```java
import java.util.*;

public class Solution {
    public static List<Long> factorialNumbers(long n) {
        List<Long> result = new ArrayList<>();
        result.add(1L);
        fillRecursive(result, n);
        return result;
    }

    private static void fillRecursive(List<Long> result, long n) {
        long numberToAdd = result.get(result.size() - 1) * (result.size() + 1);
        if (numberToAdd > n) return;
        result.add(numberToAdd);
        fillRecursive(result, n);
    }
}
```
