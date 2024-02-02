# Largest Subarray with 0 Sum

- https://www.codingninjas.com/studio/problems/longest-subarray-with-zero-sum_6783450
- only add to map if already does not exist, otherwise add - if we have a prefix sum for index i as well at index j, we would want to use i if i < j for a larger subarray

```java
import java.util.Map;
import java.util.HashMap;

public class Solution {
    public static int getLongestZeroSumSubarrayLength(int []arr){
        Map<Long, Integer> prefixSum = new HashMap<>();
        int result = 0;
        long currentSum = 0;
        prefixSum.put(0L, 0);
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (prefixSum.containsKey(currentSum)) {
                result = Math.max(result, i + 1 - prefixSum.get(currentSum));
            } else {
                prefixSum.put(currentSum, i + 1);
            }
        }
        return result;
    }
}
```
