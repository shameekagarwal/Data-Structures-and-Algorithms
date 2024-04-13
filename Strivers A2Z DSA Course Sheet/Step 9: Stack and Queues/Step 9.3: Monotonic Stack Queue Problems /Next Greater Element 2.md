# Next Greater Element 2

- https://leetcode.com/problems/next-greater-element-ii/
- for the circular variant, concatenate the array to its back again, e.g. seen [here](../../Step%205:%20Strings%20[Basic%20and%20Medium]/Step%205.1:%20Basic%20and%20Easy%20String%20Problems/Rotate%20String.md)

```java
class Solution {

    public int[] nextGreaterElements(int[] nums) {

        int[] result = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 2 * nums.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && stack.peekLast() <= nums[i % nums.length]) {
                stack.removeLast();
            }
            if (i < nums.length) {
                result[i] = stack.isEmpty() ? -1 : stack.peekLast();
            }
            stack.addLast(nums[i % nums.length]);
        }

        return result;
    }
}
```
