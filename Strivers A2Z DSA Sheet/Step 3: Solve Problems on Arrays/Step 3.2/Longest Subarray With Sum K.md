# Longest Subarray With Sum K

- https://www.codingninjas.com/studio/problems/longest-subarray-with-sum-k_6682399

```java
public class Solution {

    public static int longestSubarrayWithSumK(int []a, long k) {
        
        long currentSum = 0;
        int startOfSubarray = 0;
        int result = 0;
        
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            while (currentSum > k && startOfSubarray < i) {
                currentSum -= a[startOfSubarray];
                startOfSubarray += 1;
            }
            if (currentSum == k) {
                result = Math.max(result, i - startOfSubarray + 1);
            }
        }

        return result;
    }
}
```
