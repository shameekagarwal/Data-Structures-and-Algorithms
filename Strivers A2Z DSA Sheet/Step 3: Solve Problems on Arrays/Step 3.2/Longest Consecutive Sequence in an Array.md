# Longest Consecutive Sequence in an Array

- https://leetcode.com/problems/longest-consecutive-sequence/
- naive - sort array, then find answer by simple iteration. complexity - O(n log n)
- optimal - add to set in one iteration
- in second iteration, start counting only when current element - 1 does not exist
- assume hash set is O(1). current algorithm complexity = O(n) + O(n + n)
  - first two ns are for the two loops
  - the third n is due to the finding of max length, when current element - 1 does not exist

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int maxConsecutiveLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i] - 1)) continue;
            int current = nums[i];
            int currentLength = 0;
            while (set.contains(current)) {
                currentLength += 1;
                current += 1;
            }
            maxConsecutiveLength = Math.max(currentLength, maxConsecutiveLength);
        }
        return maxConsecutiveLength;
    }
}
```
