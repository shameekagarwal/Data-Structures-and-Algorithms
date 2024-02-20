# Move Zeroes

- https://leetcode.com/problems/move-zeroes/
- O(n) to move all non zero elements to beginning
- use another pass to convert all elements from after the last non zero element to end and set them to 0

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int zeroCount = 0;
        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[pointer] = nums[i];
                pointer += 1;
            }
        }
        for (int i = pointer; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```

## Do it in One Pass?

- beginningOfZeroes to i - 1 is a collection of zeros
- if i is also a zero - good. else, swap beginningOfZeroes with i, so that our condition of previous point is maintained

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int beginningOfZeroes = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && beginningOfZeroes == -1) {
                beginningOfZeroes = i;
            } else if (nums[i] != 0 && beginningOfZeroes != -1) {
                swap(nums, beginningOfZeroes, i);
                beginningOfZeroes += 1;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
