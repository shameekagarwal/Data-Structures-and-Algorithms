# Subsets

- https://leetcode.com/problems/subsets/
- element can be present or not present
- model all scenarios as 0 or 1

## Iterative Solution

- if nums is of length x, generate all numbers from 0 to 2^x - 1
- if ith bit in this generated number is set, consider it as a part of the set
- finally add the generated set to result

```java
class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            List<Integer> set = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    set.add(nums[j]);
                }
            }
            result.add(set);
        }
        return result;
    }
}
```

## Recursive Solution

- using a boolean array to track whether or not the element should be included in the current result

```java
class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        recurse(result, nums, 0, new ArrayList<>());
        return result;
    }

    private void recurse(List<List<Integer>> result, int[] nums, int idx, List<Integer> pick) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(pick));
            return;
        }

        recurse(result, nums, idx + 1, pick);

        pick.add(nums[idx]);
        recurse(result, nums, idx + 1, pick);
        pick.remove(pick.size() - 1);
    }
}
```
