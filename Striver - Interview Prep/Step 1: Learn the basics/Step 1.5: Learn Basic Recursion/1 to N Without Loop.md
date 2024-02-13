# 1 to N Without Loop

- function calls itself
- stack overflow - infinite recursion
- base condition to terminate this
- https://www.codingninjas.com/studio/problems/print-1-to-n_628290

```java
public class Solution {

    public static int[] printNos(int x) {
        int[] nums = new int[x];
        fillRecursively(nums, x);
        return nums;
    }

    private static void fillRecursively(int[] nums, int x) {
        if (x == 0) return;
        nums[x - 1] = x;
        fillRecursively(nums, x - 1);
    }
}
```
