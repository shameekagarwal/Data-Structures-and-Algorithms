# Partition Equal Subset Sum

- https://leetcode.com/problems/partition-equal-subset-sum/
- same as [Subset Sum Equal To K](./Subset%20Sum%20Equal%20To%20K.md)
- first, sum of all elements in the array
- if sum % 2 = 1, not possible
- else look for a subset with sum s / 2

```java
class Solution {

    public boolean canPartition(int[] nums) {
        
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 == 1) return false;

        int target = sum / 2;

        Set<Integer> previous = new HashSet<>();
        previous.add(0);

        for (int i = 0; i < nums.length; i++) {
            
            Set<Integer> current = new HashSet<>();
            for (int j = 0; j <= target; j++) {
                if (previous.contains(j) || previous.contains(j - nums[i])) {
                    current.add(j);
                }
            }

            previous = current;
        }

        return previous.contains(target);
    }
}
```
