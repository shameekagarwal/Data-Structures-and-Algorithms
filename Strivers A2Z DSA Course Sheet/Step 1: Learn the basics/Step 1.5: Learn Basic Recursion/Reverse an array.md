# Reverse an array

- O(n) is naive, do it in O(n / 2) is the complexity
- best practice - since return was expected, do not mutate user input
- https://www.codingninjas.com/studio/problems/reverse-an-array_8365444

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
        reversedNums[indexToSwap] = nums[rightIndexToSwap];
        reversedNums[rightIndexToSwap] = nums[indexToSwap];
        reverseArrayRecursive(nums, reversedNums, indexToSwap + 1);
    }
}
```
