# Binary Subarrays With Sum

- naive - O(n^2)
- first thing to come to mind should be [Subarray Sum Equals K](../../Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.2:%20Medium/Subarray%20Sum%20Equals%20K.md)
- time complexity - O(n), space complexity - O(n)

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {

        int[] prefixSumLookup = new int[nums.length + 1];
        int prefixSum = 0;
        prefixSumLookup[0] = 1;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum >= goal) {
                result += prefixSumLookup[prefixSum - goal];
            }
            prefixSumLookup[prefixSum] += 1;
        }

        return result;
    }
}
```

## Optimal

- time complexity - O(2*n), space complexity - O(1)
- count of subarrays with sum at most goal - count of subarrays with sum at most (goal - 1)
- i think this approach would work for [Subarray Sum Equals K](../../Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.2:%20Medium/Subarray%20Sum%20Equals%20K.md) as well as long as k is positive?
- l points to the element where the subarray for current r can start - once l starts pointing to r, it means size of subarray is 1 - and we cannot proceed l further
- so, we put the condition l <= r - when l = r, currentSum should be 0 by my logic. but when goal is -1, l would try going below 0 i.e. exceeding r as well, which can result in an array out of bounds exception
- why the general two pointer would not work according to my understanding - say goal is 4. r would point to 7 and l to 0. now, once r is incremented to 8, l could not continue pointing to 0, so it is moved to 1. but, we missed 1-7, 2-7, 3-7, etc as potential answers
  ```
  elements - 0 0 0 0 1 1 1 1 1
  index    - 0 1 2 3 4 5 6 7 8
  ```

```java
class Solution {

    public int numSubarraysWithSum(int[] nums, int goal) {
        return (int) (numSubarraysWithSumAtMost(nums, goal) - numSubarraysWithSumAtMost(nums, goal - 1));
    }

    private long numSubarraysWithSumAtMost(int[] nums, int goal) {

        int l = 0;
        int currentSum = 0;
        long result = 0;

        for (int r = 0; r < nums.length; r++) {
            currentSum += nums[r];
            while (l <= r && currentSum > goal) {
                currentSum -= nums[l];
                l += 1;
            }
            result += (r - l);
        }

        return result;
    }
}
```
