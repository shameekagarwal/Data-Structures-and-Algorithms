# Find Nth Root Of M

- https://www.codingninjas.com/studio/problems/nth-root-of-m_1062679
- time complexity - log2m * log2n - log2m for binary search, log2n for binary exponentiation
- while it does not happen here somehow, if we do binary exponentiation, our result might overflow - so, instead of calculating the ultimate result of `pow(mid, n)`, quit midway once it exceeds m - the function will now change to `pow(mid, n, m)` and return 0 if equal, 1 if smaller, 2 if greater

```java
public class Solution {
    public static int NthRoot(int n, int m) {
        
        int low = 1;
        int high = m;

        while (low <= high) {
            
            int mid = low + ((high - low) / 2);
            long midRaisedToN = (long) Math.pow(mid, n);
            if (midRaisedToN == m) {
                return mid;
            } else if (midRaisedToN > m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
```
