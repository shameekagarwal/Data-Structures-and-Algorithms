# Remove Element

- https://leetcode.com/problems/remove-element/
- keep reading, but write only if `nums[i] != val`
- the final value of ptr can be returned

```java
class Solution {

    public int removeElement(int[] nums, int val) {

        int ptr = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {
                nums[ptr] = nums[i];
                ptr += 1;
            }
        }

        return ptr;
    }
}
```
