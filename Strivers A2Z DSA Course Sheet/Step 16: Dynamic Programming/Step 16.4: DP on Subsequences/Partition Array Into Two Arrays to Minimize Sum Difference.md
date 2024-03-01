# Partition Array Into Two Arrays to Minimize Sum Difference

- https://www.codingninjas.com/studio/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum._842494
- we only need to calculate one side, let its sum be s1
- the other side will automatically be sum - s1
- trick - generate all possible subset sums using [Subset Sum Equal To K](./Subset%20Sum%20Equal%20To%20K.md)
- now, try checking for every sum in this generated list, assume s1 is this, we can calculate s2 using s - s1
- now, take min of all s1 - (s - s1)

```java
import java.util.*;

public class Solution {

    public static int minSubsetSumDifference(int []nums, int n) {
        
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        boolean[] previous = new boolean[sum + 1];
        previous[0] = true;

        for (int i = 0; i < nums.length; i++) {

            boolean[] current = new boolean[sum + 1];

            for (int j = 0; j <= sum; j++) {
                current[j] = previous[j];
                if (j >= nums[i]) {
                    current[j] = (current[j] | previous[j - nums[i]]);
                }
            }

            previous = current;
        }

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= sum; i++) {
            if (previous[i]) {
                minDiff = Math.min(minDiff, Math.abs(2 * i - sum));
            }
        }
        return minDiff;
    }
}
```
