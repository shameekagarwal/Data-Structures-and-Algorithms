# Points

- https://leetcode.com/problems/subarray-sum-equals-k/
- make a map of prefixSum, count
- iterate one by one
- total sub arrays with sum k = 
  - if current prefix sum is k, add 1
  - all prefix sums which have value (current prefix sum - k), because that means all up to current element is k
    ```
    - - - - - - - - - -
      <---- k ---->
          <-- k -->
            <- k ->
    ```

# Solution

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefix = new HashMap<>();
        int currentSum = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (currentSum == k) {
                result += 1;
            }
            if (prefix.containsKey(currentSum - k)) {
                result += prefix.get(currentSum - k);
            }
            prefix.put(currentSum, prefix.getOrDefault(currentSum, 0) + 1);
        }
        return result;
    }
}
```
