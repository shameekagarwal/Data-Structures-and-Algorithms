# Reverse Pairs

- https://leetcode.com/problems/reverse-pairs/
- exact same as [Number of Inversions](./Number%20of%20Inversions.md), just condition has changed

```java
class Solution {

    public int reversePairs(int[] nums) {
        return reversePairs(nums, 0, nums.length - 1);
    }

    private int reversePairs(int[] nums, int leftStart, int rightEnd) {

        if (leftStart >= rightEnd) return 0;

        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd + 1;

        int leftReversals = reversePairs(nums, leftStart, leftEnd);
        int rightReversals = reversePairs(nums, rightStart, rightEnd);
        int currentReversals = countReversals(nums, leftStart, leftEnd, rightStart, rightEnd);
        merge(nums, leftStart, leftEnd, rightStart, rightEnd);

        // System.out.printf("[%d, %d, %d, %d]: %d + %d + %d\n", leftStart, leftEnd, rightStart, rightEnd, leftReversals, rightReversals, currentReversals);

        return leftReversals + rightReversals + currentReversals;
    }

    private int countReversals(int[] nums, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        
        int reversals = 0;
        int leftPointer = leftStart;
        int rightPointer = rightStart;

        while (leftPointer <= leftEnd && rightPointer <= rightEnd) {
            while (rightPointer <= rightEnd && nums[leftPointer] > 2L * nums[rightPointer]) {
                rightPointer += 1;
            }
            reversals += (rightPointer - rightStart);
            leftPointer += 1;
        }
        
        reversals += (leftEnd - leftPointer + 1) * (rightPointer - rightStart);

        return reversals;
    }

    private void merge(int[] nums, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        
        int merged[] = new int[rightEnd - leftStart + 1];
        int leftPointer = leftStart;
        int rightPointer = rightStart;
        int mergedPointer = 0;

        while (leftPointer <= leftEnd && rightPointer <= rightEnd) {
            if (nums[leftPointer] < nums[rightPointer]) {
                merged[mergedPointer] = nums[leftPointer];
                leftPointer += 1;
                mergedPointer += 1;
            } else {
                merged[mergedPointer] = nums[rightPointer];
                rightPointer += 1;
                mergedPointer += 1;
            }
        }

        while (leftPointer <= leftEnd) {
            merged[mergedPointer] = nums[leftPointer];
            leftPointer += 1;
            mergedPointer += 1;
        }

        while (rightPointer <= rightEnd) {
            merged[mergedPointer] = nums[rightPointer];
            rightPointer += 1;
            mergedPointer += 1;
        }

        for (int i = 0; i < mergedPointer; i++) {
            nums[i + leftStart] = merged[i];
        }
    }
}
```
