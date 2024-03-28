# Split the Array

- https://leetcode.com/problems/split-the-array/

```java
class Solution {
    public boolean isPossibleToSplit(int[] nums) {
        int[] map = new int[101];
        for (int i : nums) {
            if (map[i] == 2) {
                return false;
            }
            map[i] += 1;
        }
        return true;
    }
}
```
