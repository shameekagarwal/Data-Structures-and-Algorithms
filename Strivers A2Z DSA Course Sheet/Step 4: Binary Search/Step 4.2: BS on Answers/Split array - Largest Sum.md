# Split array - Largest Sum

- https://leetcode.com/problems/split-array-largest-sum/
- same as [Book Allocation Problem](./Book%20Allocation%20Problem.md)

```java
class Solution {

    private int[] nums;

    private int numOfSubArrays;

    public int splitArray(int[] nums, int k) {
        this.nums = nums;
        this.numOfSubArrays = k;
        return run();
    }

    private int run() {
        
        int maxPossibleSum = getMaxPossibleSum();
        int minPossibleSum = getMinPossibleSum();
        int result = maxPossibleSum;
        
        while (minPossibleSum <= maxPossibleSum) {
            
            int possibleResult = minPossibleSum + ((maxPossibleSum - minPossibleSum) / 2);

            if (isPossible(possibleResult)) {
                result = possibleResult;
                maxPossibleSum = possibleResult - 1;
            } else {
                minPossibleSum = possibleResult + 1;
            }
        }
        
        return result;
    }

    private boolean isPossible(int possibleResult) {
        int currentNumOfSubArrays = 0;
        int currentSubArraySum = 0;
        for (int num : nums) {
            if (currentSubArraySum + num > possibleResult) {
                currentNumOfSubArrays += 1;
                currentSubArraySum = 0;
            }
            currentSubArraySum += num;
        }
        currentNumOfSubArrays += 1;
        // System.out.printf("no of subarrays for %d = %d\n", possibleResult, currentNumOfSubArrays);
        return currentNumOfSubArrays <= numOfSubArrays;
    }

    private int getMaxPossibleSum() {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i];
        }
        return max;
    }

    private int getMinPossibleSum() {
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.max(min, nums[i]);
        }
        return min;
    }
}
```
