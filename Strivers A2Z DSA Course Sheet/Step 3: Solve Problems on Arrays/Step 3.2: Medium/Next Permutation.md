# Next Permutation

- https://leetcode.com/problems/next-permutation/
- brute force - generate all permutations, O(n! * n) (n! permutations, n is size of each permutation)
- optimal - lets assume number if - 2 1 5 4 3 0 0
- we if we keep 2 and 1 as is, the remaining is the max possible
- we need to exchange 1 with the "next largest number" after it - 3 in this case
- now, we make the number 2 3 [5 4 1 0 0]
- now, 5 4 1 0 0 we need to make smallest possible - just reverse it!
- small edge case i made mistake - when reversing, e.g. assume it is 5 5 5 5
- we need to swap with last 5, not first - otherwise reversing will not make it ascending

```java
class Solution {
    public void nextPermutation(int[] nums) {

        int indexToSwap = -1;

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                indexToSwap = i - 1;
                break;
            }
        }

        if (indexToSwap == -1) {
            Arrays.sort(nums);
            return;
        }

        int justMoreThanIndexToSwapIndex = -1;
        for (int i = indexToSwap + 1; i < nums.length; i++) {
            if ((justMoreThanIndexToSwapIndex == -1 || nums[justMoreThanIndexToSwapIndex] >= nums[i]) && nums[i] > nums[indexToSwap]) {
                justMoreThanIndexToSwapIndex = i;
            }
        }

        swap(nums, justMoreThanIndexToSwapIndex, indexToSwap);
        reverse(nums, indexToSwap + 1, nums.length - 1);
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private void reverse(int[] nums, int x, int y) {
        for (int i = 0; i < (y - x + 1) / 2; i++) {
            swap(nums, i + x, y - i);
        }
    }
}
```
