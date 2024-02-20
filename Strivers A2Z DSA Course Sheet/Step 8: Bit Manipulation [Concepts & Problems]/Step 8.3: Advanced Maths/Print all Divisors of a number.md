# Print all Divisors of a number

- https://www.codingninjas.com/studio/problems/print-all-divisors-of-a-number_1164188

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    
    public static List<Integer> printDivisors(int n) {
        
        List<Integer> factors = new ArrayList<>();
        
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                if (n / i != i) {
                    factors.add(n / i);
                }
            }
        }

        Collections.sort(factors);

        return factors;
    }
}
```
