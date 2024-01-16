# Longest Subarray With Sum K

## Both Positives and Negatives

- https://www.codingninjas.com/studio/problems/longest-subarray-with-sum-k_5713505

```java
import java.util.*;
import java.io.*;

public class Solution {

	public static int getLongestSubarray(int []nums, int k) {
		
		Map<Long, Integer> prefixSumFirstSeen = new HashMap<Long, Integer>();
		long prefixSum = 0;
		int result = 0;
		prefixSumFirstSeen.put(0L, -1);

		for (int i = 0; i < nums.length; i++) {
			
			prefixSum += nums[i];
			
			if (prefixSumFirstSeen.containsKey(prefixSum - k)) {
				result = Math.max(result, i - prefixSumFirstSeen.get(prefixSum - k));
			}
			
			if (!prefixSumFirstSeen.containsKey(prefixSum)) {
				prefixSumFirstSeen.put(prefixSum, i);
			}
		}

		return result;
	}
}
```

## Only Positives

- https://www.codingninjas.com/studio/problems/longest-subarray-with-sum-k_6682399
- idea - we can get rid of the complexity that comes from maps when array has only positives by using two pointer approach

```java
public class Solution {
    public static int longestSubarrayWithSumK(int []a, long k) {
        int pointerFirst = 0;
        int currentSum = 0;
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            while (pointerFirst < i && currentSum > k) {
                currentSum -= a[pointerFirst];
                pointerFirst += 1;
            }
            if (currentSum == k) {
                result = Math.max(result, i - pointerFirst + 1);
            }
        }
        return result;
    }
}
```
