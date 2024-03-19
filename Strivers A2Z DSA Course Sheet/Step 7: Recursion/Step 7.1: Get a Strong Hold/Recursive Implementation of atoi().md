# Recursive Implementation of atoi()

- https://leetcode.com/problems/string-to-integer-atoi/
- kept it the same as [String to Integer (atoi)](../../Step%205:%20Strings%20[Basic%20and%20Medium]/Step%205.2:%20Medium%20String%20Problems/String%20to%20Integer%20(atoi).md), but just made the number calculation bit recursive

```java
class Solution {
    public int myAtoi(String s) {
        
        char[] nums = s.toCharArray();
        int idx = 0;
        
        // step 1
        while (idx < nums.length && nums[idx] == ' ') {
            idx += 1;
        }

        // step 2
        boolean isNegative = false;
        if (idx < nums.length && (nums[idx] == '+' || nums[idx] == '-')) {
            isNegative = nums[idx] == '-';
            idx += 1;
        }

        return rec(nums, 0, isNegative, idx);
    }

    private int rec(char[] nums, int value, boolean isNegative, int idx) {
        
        if (idx == nums.length) return isNegative ? value * -1 : value;
        if (nums[idx] < '0' || nums[idx] > '9') return isNegative ? value * -1 : value;
        
        int digit = nums[idx] - '0';
        if (value > (Integer.MAX_VALUE - digit) / 10) return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        return rec(nums, value * 10 + digit, isNegative, idx + 1);
    }
}
```
