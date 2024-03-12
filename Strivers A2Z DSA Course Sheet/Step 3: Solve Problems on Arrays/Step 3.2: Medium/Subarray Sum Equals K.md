# Subarray Sum Equals K

- https://leetcode.com/problems/subarray-sum-equals-k/
- for all subarray equals sum k, recall [this](../Step%203.1:%20Easy/Longest%20Subarray%20With%20Sum%20K.md) - if only positive integers are there, we might get away without using a map and only two pointers
- note the initialization for 0 to make things easier
- both time and space complexity - O(n)

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        int currentSum = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (prefix.containsKey(currentSum - k)) {
                result += prefix.get(currentSum - k);
            }
            prefix.put(currentSum, prefix.getOrDefault(currentSum, 0) + 1);
        }
        return result;
    }
}
```
