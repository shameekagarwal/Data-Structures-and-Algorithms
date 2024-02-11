# Largest Subarray with 0 Sum

- https://www.codingninjas.com/studio/problems/longest-subarray-with-zero-sum_6783450
- exact same as [this](/Strivers%20A2Z%20DSA%20Sheet/Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.1:%20Easy/Longest%20Subarray%20With%20Sum%20K.md)

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
