# Points

- https://leetcode.com/problems/longest-consecutive-sequence/
- add all elements to set
- for current element - 
  - keep adding to current count till +1 found
  - keep adding to current count till -1 found
  - as and when we add elements to the current sequence, remove them from the set so that they are not considered again for counting sequence in the future

# Solution

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        int output = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) continue;

            int currentMax = 1;
            set.remove(nums[i]);
            
            int temp = nums[i] + 1;
            while (set.contains(temp)) {
                set.remove(temp);
                currentMax += 1;
                temp += 1;
            }

            temp = nums[i] - 1;
            while (set.contains(temp)) {
                set.remove(temp);
                currentMax += 1;
                temp -= 1;
            }

            output = Math.max(output, currentMax);
        }
        return output;
    }
}
```
