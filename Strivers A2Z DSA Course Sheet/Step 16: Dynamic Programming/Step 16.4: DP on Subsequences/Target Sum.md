# Target Sum

- https://leetcode.com/problems/target-sum/
- can be re imagined like [Partitions With Given Difference](./Partitions%20With%20Given%20Difference.md)

```java
class Solution {

    public int findTargetSumWays(int[] nums, int target) {
        
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int s1 = (sum + target);
        if (s1 < 0 || s1 % 2 != 0) return 0;
        s1 /= 2;

        int[] previous = new int[s1 + 1];
        previous[0] = 1;

        for (int i : nums) {

            int[] current = new int[s1 + 1];

            for (int j = 0; j <= s1; j++) {
                current[j] = previous[j];
                if (i <= j) {
                    current[j] += previous[j - i];
                }
            }
            
            previous = current;
        }

        return previous[s1];
    }
}
```
