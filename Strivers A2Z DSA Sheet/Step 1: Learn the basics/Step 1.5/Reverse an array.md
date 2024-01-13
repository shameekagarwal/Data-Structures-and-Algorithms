# Reverse an array

- O(n / 2) is the complexity
- best practice - since return was expected, do not mutate user input

```java
public class Solution {
    public static int[] reverseArray(int n, int []nums) {
        int[] reversedNums = new int[nums.length];
        reverseArrayRecursive(nums, reversedNums, 0);
        return reversedNums;
    }

    private static void reverseArrayRecursive(int nums[], int reversedNums[], int indexToSwap) {
        int rightIndexToSwap = nums.length - 1 - indexToSwap;
        if (rightIndexToSwap < indexToSwap) return;
        if (rightIndexToSwap < indexToSwap) {
            reversedNums[indexToSwap] = nums[indexToSwap];
            return;
        }
        reversedNums[indexToSwap] = nums[rightIndexToSwap];
        reversedNums[rightIndexToSwap] = nums[indexToSwap];
        reverseArrayRecursive(nums, reversedNums, indexToSwap + 1);
    }
}
```
