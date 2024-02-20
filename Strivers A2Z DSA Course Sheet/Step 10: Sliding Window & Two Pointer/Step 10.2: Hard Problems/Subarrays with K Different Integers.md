# Subarrays with K Different Integers

- https://leetcode.com/problems/subarrays-with-k-different-integers/
- same as [Count Number of Nice Subarrays](../Step%2010.1:%20Medium%20Problems/Count%20Number%20of%20Nice%20Subarrays.md)

```java
class Solution {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return  subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1);
    }

    private int subarraysWithAtMostKDistinct(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int l = 0;
        int result = 0;
        for (int r = 0; r < nums.length; r++) {
            frequency.put(nums[r], frequency.getOrDefault(nums[r], 0) + 1);
            while (frequency.size() > k) {
                if (frequency.get(nums[l]) == 1) {
                    frequency.remove(nums[l]);
                } else {
                    frequency.put(nums[l], frequency.getOrDefault(nums[l], 0) - 1);
                }
                l += 1;
            }
            result += (r - l);
        }
        return result;
    }
}
```
