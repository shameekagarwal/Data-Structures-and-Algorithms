# Longest Common Prefix

- https://leetcode.com/problems/longest-common-prefix/
- observation - `common_prefix(common_prefix(common_prefix(arr[0], arr[1]), arr[2]), arr[3]) ...`
- assign common prefix as first string
- iterate through entire list of strings
- compare character by character while common prefix and current string are equal
- finally, break out of comparison in case of mismatch and change the common prefix
- complexity - O(n * str_size)
- some people in discussions sorting but why, i think mine is better time complexity?
  - sort the array
  - find common prefix between first and last string of array
  - observation - since the first and last should be the most widely "apart"
  - complexity should be O(nlogn*str_size) for sorting (not just nlogn)?
  - another extra O(str_size) for comparing first and last string

```java
class Solution {

    public String longestCommonPrefix(String[] strs) {
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < Math.min(strs[i].length(), commonPrefix.length())) {
                if (strs[i].charAt(j) != commonPrefix.charAt(j)) {
                    break;
                }
                j++;
            }
            commonPrefix = commonPrefix.substring(0, j);
        }
        return commonPrefix;
    }
}
```
