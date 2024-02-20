#	Print Prime Factors of a Number

- https://www.codingninjas.com/studio/problems/prime-factorisation_1760849

```java
import java.util.List;
import java.util.ArrayList;

public class Solution {
    
    public static List<Integer> countPrimes(int n) {

        List<Integer> result = new ArrayList<>();

        n = tryPrimeFactor(n, 2, result);

        for (int i = 3; i * i <= n; i++) {
            n = tryPrimeFactor(n, i, result);
        }

        if (n > 1) {
            result.add(n);
        }

        return result;
    }

    private static int tryPrimeFactor(int n, int factor, List<Integer> factors) {
        if (n % factor == 0) {
            factors.add(factor);
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n;
    }
}
```
