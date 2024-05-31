# Maximum Number of Jumps to Reach the Last Index

- https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/
- first, construct the jumps list - we can jump from i to j such that j > i if `abs(nums[i] - nums[j]) <= target`
- now, use this jumps to keep jumping
- calculate the max obtainable for every jump
- if not possible, return -1
- if value returned from jump is not -1, maximize value with returned value + 1
- that is why i initialize dp with -2
- thought - can avoid the extra pre computation of jumps - we reduce space this way - we calculate the possible jumps on fly

```java
class Solution {

    public int maximumJumps(int[] nums, int target) {

        List<List<Integer>> jumps = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            jumps.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    jumps.get(i).add(j);
                }
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, -2);

        return recurse(0, jumps, dp);
    }

    private int recurse(int idx, List<List<Integer>> jumps, int[] dp) {

        if (idx == jumps.size() - 1) {
            return 0;
        }

        if (dp[idx] != -2) {
            return dp[idx];
        }

        int result = -1;

        for (int jump : jumps.get(idx)) {

            int value = recurse(jump, jumps, dp);

            if (value != -1) {
                result = result == -1 ? value + 1 : Math.max(result, value + 1);
            }
        }

        dp[idx] = result;

        return result;
    }
}
```
