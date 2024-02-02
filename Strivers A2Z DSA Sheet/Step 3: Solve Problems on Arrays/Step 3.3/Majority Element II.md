# Majority Element II

- https://leetcode.com/problems/majority-element-ii/
- naive - space and time both O(n) using hash map
- intuition - same as [Majority Element](/Strivers%20A2Z%20DSA%20Sheet/Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.2/Majority%20Element.md)
- at max, two elements can satisfy the criteria - more than 2 elements cannot be > n / 3
- we need to run the check at the end like discussed in Majority Element
- my doubt - why do we not decrease the count of majority 1 when for e.g. majority 2 is found? - because majority 2 is not competing with majority 1 - only elements that compete with majority 1 can decrease / affect its count
- this is why, only the final condition is -1 for both, because it is competing to become both either majority 1 and majority 2 - the current element is -
  - neither equal to either of the majorities
  - nor the possible candidate since count of either of the majorities reached 0

```java
class Solution {
    
    public List<Integer> majorityElement(int[] nums) {
        
        int currentMajorityOne = Integer.MIN_VALUE;
        int currentMajorityOneCount = 0;

        int currentMajorityTwo = Integer.MIN_VALUE;
        int currentMajorityTwoCount = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (currentMajorityOne == nums[i]) {
                currentMajorityOneCount += 1;
            } else if (currentMajorityTwo == nums[i]) {
                currentMajorityTwoCount += 1;
            } else if (currentMajorityOneCount == 0) {
                currentMajorityOne = nums[i];
                currentMajorityOneCount = 1;
            } else if (currentMajorityTwoCount == 0) {
                currentMajorityTwo = nums[i];
                currentMajorityTwoCount = 1;
            } else {
                currentMajorityOneCount -= 1;
                currentMajorityTwoCount -= 1;
            }
        }

        currentMajorityOneCount = 0;
        currentMajorityTwoCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentMajorityOne == nums[i]) {
                currentMajorityOneCount += 1;
            } else if (currentMajorityTwo == nums[i]) {
                currentMajorityTwoCount += 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (currentMajorityOneCount > nums.length / 3) {
            result.add(currentMajorityOne);
        }
        if (currentMajorityTwoCount > nums.length / 3) {
            result.add(currentMajorityTwo);
        }
        return result;
    }
}
```
