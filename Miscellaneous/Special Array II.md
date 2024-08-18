# Special Array II

- https://leetcode.com/problems/special-array-ii/
- "falses" - a prefix sum, which tells us number of falses found upto i
- i accounts for `nums[i] vs nums[i - 1]` parity
- now, we just have to ensure number of falses in the range is 0

```java
class Solution {

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {

        int n = nums.length;
        int[] falses = new int[n];

        for (int i = 1; i < nums.length; i++) {

            falses[i] = falses[i - 1];

            if (((nums[i] ^ nums[i - 1]) & 1) != 1) {
                falses[i] += 1;
            }
        }

        int q = queries.length;
        boolean[] result = new boolean[q];

        for (int i = 0; i < q; i++) {
            result[i] = falses[queries[i][1]] - falses[queries[i][0]] == 0;
        }

        return result;
    }
}
```
