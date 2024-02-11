# Highest / Lowest Frequency Elements

- https://www.codingninjas.com/studio/problems/k-most-occurrent-numbers_625382
- please remember `entrySet()`

```java
import java.util.*;

public class Solution {
    public static int[] getFrequencies(int []v) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < v.length; i++) {
            map.put(v[i], map.getOrDefault(v[i], 0) + 1);
        }
        
        int numberWithMaxCountSeenTillNow = -1;
        int maxCountSeenTillNow = Integer.MIN_VALUE;

        int numberWithMinCountSeenTillNow = -1;
        int minCountSeenTillNow = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            if (entry.getValue() > maxCountSeenTillNow) {
                maxCountSeenTillNow = entry.getValue();
                numberWithMaxCountSeenTillNow = entry.getKey();
            } else if (entry.getValue() == maxCountSeenTillNow && numberWithMaxCountSeenTillNow > entry.getKey()) {
                numberWithMaxCountSeenTillNow = entry.getKey();
            }

            if (entry.getValue() < minCountSeenTillNow) {
                minCountSeenTillNow = entry.getValue();
                numberWithMinCountSeenTillNow = entry.getKey();
            } else if (entry.getValue() == minCountSeenTillNow && numberWithMinCountSeenTillNow > entry.getKey()) {
                numberWithMinCountSeenTillNow = entry.getKey();
            }
        }

        int[] retval = new int[]{numberWithMaxCountSeenTillNow, numberWithMinCountSeenTillNow};
        return retval;
    }
}
```
