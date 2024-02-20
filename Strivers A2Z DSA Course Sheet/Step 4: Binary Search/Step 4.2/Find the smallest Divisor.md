# Find the smallest Divisor

- if we set low to 1
- high to max element + 1
- answer would be 0, which should be <= threshold
- brute - linear search - complexity - O(max_ele * n)
- optimal - binary search - O(log(max_ele) * n)
- https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/

```java
class Solution {

    private int[] nums;
    private int threshold;

    public int smallestDivisor(int[] nums, int threshold) {

        this.nums = nums;
        this.threshold = threshold;

        int low = 1;
        int high = getMaxDivisor();
        int minDivisor = high;

        while (low <= high) {
            
            int currentDivisor = low + ((high - low) / 2);
            if (isCurrentDivisorValid(currentDivisor)) {
                minDivisor = currentDivisor;
                high = currentDivisor - 1;
            } else {
                low = currentDivisor + 1;
            }
        }

        return minDivisor;
    }

    private int getMaxDivisor() {

        int maxDivisor = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxDivisor = Math.max(maxDivisor, nums[i]);
        }
        return maxDivisor + 1;
    }

    private boolean isCurrentDivisorValid(int currentDivisor) {
        
        int currentValue = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currentValue += Math.ceil((double) nums[i] / currentDivisor);
        }

        return currentValue <= threshold;
    }
}
```
