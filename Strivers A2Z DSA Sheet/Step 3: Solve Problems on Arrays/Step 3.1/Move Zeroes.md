# Move Zeroes

- https://leetcode.com/problems/move-zeroes/

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

- the pointer always points to the beginning of zeroes

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
