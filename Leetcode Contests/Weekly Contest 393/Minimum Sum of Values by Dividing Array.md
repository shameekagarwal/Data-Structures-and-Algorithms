# Minimum Sum of Values by Dividing Array

- https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array/
- might feel like `m * n * nums[i]`
- but we only have approximately "bits" number of possibilities for runningAnd - a bit can only be turned off, so 1 can only become 0
- so, the time complexity comes down to a constant, effectively becoming `m * n`

```java
class Solution {

    public int minimumValueSum(int[] nums, int[] andValues) {
        
        int n = nums.length;
        int m = andValues.length;

        Map<Integer, Map<Integer, Map<Integer, Integer>>> dp = new HashMap<>();
        
        return minimumValueSum(nums, andValues, n, m, 0, 0, Integer.MAX_VALUE, dp);
    }

    private int minimumValueSum(int[] nums, int[] andValues, int n, int m, int numsIdx, int andValuesIdx, int runningAnd, Map<Integer, Map<Integer, Map<Integer, Integer>>> dp) {

        if (andValuesIdx == m && numsIdx == n) {
            return 0;
        } else if (andValuesIdx == m || numsIdx == n) {
            return -1;
        }

        if (dp.containsKey(numsIdx)) {
            if (dp.get(numsIdx).containsKey(andValuesIdx)) {
                if (dp.get(numsIdx).get(andValuesIdx).containsKey(runningAnd)) {
                    return dp.get(numsIdx).get(andValuesIdx).get(runningAnd);
                }
            } else {
                dp.get(numsIdx).put(andValuesIdx, new HashMap<>());
            }
        } else {
            dp.put(numsIdx, new HashMap<>());
            dp.get(numsIdx).put(andValuesIdx, new HashMap<>());
        }

        runningAnd &= nums[numsIdx];
        
        int result = minimumValueSum(nums, andValues, n, m, numsIdx + 1, andValuesIdx, runningAnd & nums[numsIdx], dp);
        
        if (runningAnd == andValues[andValuesIdx]) {
            
            int val = minimumValueSum(nums, andValues, n, m, numsIdx + 1, andValuesIdx + 1, Integer.MAX_VALUE, dp);
            
            if (val != -1 && (result == -1 || val + nums[numsIdx] < result)) {
                result = val + nums[numsIdx];
            }
        }

        dp.get(numsIdx).get(andValuesIdx).put(runningAnd, result);

        return result;
    }
}
```
