# Count All Subarrays With Given Sum

- https://www.codingninjas.com/studio/problems/subarray-sums-i_1467103
- done with slight modifications to [Subarrays with Sum ‘k'](./Subarrays%20with%20Sum%20‘k'.md)
- i think this approach worked since all were positive integers, otherwise there could be multiple sub arrays ending at i with sum k - in which case my Map would be `List<Long, Integer>` i.e. contain a count of indices for a particular prefix sum 

```java
import java.util.* ;
import java.io.*;

public class Solution {
    
    public static int findAllSubarraysWithGivenSum(int nums[], int k) {

        Set<Long> set = new HashSet<>();
        long currentSum = 0;
        set.add(0L);
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (set.contains(currentSum - k)) {
                result += 1;
            }
            set.add(currentSum);
        }

        return result;
    }
}
```
