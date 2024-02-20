# Find Nth Root Of M

- https://www.codingninjas.com/studio/problems/nth-root-of-m_1062679

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
