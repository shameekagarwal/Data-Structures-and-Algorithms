# Largest Divisible Subset

- https://leetcode.com/problems/largest-divisible-subset/
- elements are unique
- print any answer
- we sort array first
- e.g. we pick indices i, j, k such that i < j < k
- if `nums[j] % nums[i] == 0`, we just need to check for `nums[k] % nums[j]` - we can omit checking `nums[k] % nums[i]`
- now, solve this question like better version of [Longest Increasing Subsequence](./Longest%20Increasing%20Subsequence.md)
- question does not ask to maintain idx, otherwise could have used hash map etc

```java
class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums) {

        int n = nums.length;

        Arrays.sort(nums);

        int[] longestSeq = new int[n];
        Arrays.fill(longestSeq, 1);

        int[] prevIdx = new int[n];
        Arrays.fill(prevIdx, -1);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && longestSeq[j] + 1 > longestSeq[i]) {
                    longestSeq[i] = longestSeq[j] + 1;
                    prevIdx[i] = j;
                }
            }
        }

        int longest = 0;
        int endIdx = -1;
        
        for (int i = 0; i < n; i++) {
            if (longestSeq[i] > longest) {
                longest = longestSeq[i];
                endIdx = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (endIdx != -1) {
            result.add(nums[endIdx]);
            endIdx = prevIdx[endIdx];
        }

        return result;
    }
}
```
