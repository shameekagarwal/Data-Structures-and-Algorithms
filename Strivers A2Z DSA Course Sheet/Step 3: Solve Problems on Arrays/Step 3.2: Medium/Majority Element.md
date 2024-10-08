# Majority Element

- https://leetcode.com/problems/majority-element/
- naive - use a map - complexity - O(n) (remember in interview to point out hashing collision therefore O(n^2))
- optimal - moore's voting algorithm
- intuition - if an element occurs more than n / 2 times, the running count we maintain should not touch 0
- note - the answer we get like this might be the majority element, but it is not guaranteed
- e.g. - 2 2 2 2 2 1 1 1 1 1 3 3 - by moore's, answer would be 3, but it isn't - we got the answer because it is "end heavy"
- so, we run an extra loop at the end
- imp note - i did not run the extra loop in leetcode since the question guaranteed that a majority element would exist
- remember the order of conditions - helps use the same logic in [Majority II](../Step%203.3:%20Hard/Majority%20Element%20II.md)

```java
class Solution {
    public int majorityElement(int[] nums) {
        int currentMajority = Integer.MIN_VALUE;
        int currentMajorityCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentMajority == nums[i]) {
                currentMajorityCount += 1;
            } else if (currentMajorityCount == 0) {
                currentMajority = nums[i];
                currentMajorityCount = 1;
            } else {
                currentMajorityCount -= 1;
            }
        }
        // currentMajorityCount = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     currentMajorityCount += currentMajority == nums[i] ? 1 : 0;
        // }
        // return currentMajorityCount > nums.length / 2 ? currentMajority : Integer.MIN_VALUE;
        return currentMajority;
    }
}
```
