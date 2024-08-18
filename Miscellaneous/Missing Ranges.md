# Missing Ranges

- https://leetcode.com/problems/missing-ranges/
- had missed one edge case - if nums.length is 0

```java
class Solution {

    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {

        if (nums.length == 0) {
            return List.of(List.of(lower, upper));
        }

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;

        if (nums[0] != lower) {
            result.add(List.of(lower, nums[0] - 1));
        }

        for (int i = 1; i < n; i++) {

            if (nums[i] != nums[i - 1] + 1) {
                result.add(List.of(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        if (nums[n - 1] != upper) {
            result.add(List.of(nums[n - 1] + 1, upper));
        }

        return result;
    }
}
```
