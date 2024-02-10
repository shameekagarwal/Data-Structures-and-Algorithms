# Subsets II

- https://leetcode.com/problems/subsets-ii/
- intuition explained inside [Combination Sum II](./Combination%20Sum%20II.md)
- remember this pattern as is to avoid confusion - notice how there is no base case here unlike what we usually see in recursion

```java
class Solution {
    
    public List<List<Integer>> subsetsWithDup(int[] _nums) {
        
        int[] nums = Arrays.copyOfRange(_nums, 0, _nums.length);
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        recurse(result, nums, 0, new ArrayList<>());
        return result;
    }

    void recurse(List<List<Integer>> result, int[] nums, int startIdx, List<Integer> pick) {
        
        result.add(new ArrayList<>(pick));

        for (int i = startIdx; i < nums.length; i++) {
            if (i != startIdx && nums[i] == nums[i - 1]) continue;
            pick.add(nums[i]);
            recurse(result, nums, i + 1, pick);
            pick.remove(pick.size() - 1);
        }
    }
}
```
