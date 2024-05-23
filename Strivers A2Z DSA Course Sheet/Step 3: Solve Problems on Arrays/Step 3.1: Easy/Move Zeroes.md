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

- same as above - but why run an extra loop at end to put 0s
- maintain a pointer to the "beginning of zeroes"
- basically when we are at i
  - 0...ptr-1 is non zero
  - ptr...i-1 is 0s
- so, we make i as 0 as well

```java
class Solution {

    public void moveZeroes(int[] nums) {

        int ptr = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, ptr);
                ptr += 1;
            }
        }
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```
