# Combination Sum

- https://leetcode.com/problems/combination-sum/
- two recursive calls
  - use the current element and stay at current index
  - do not use the current element but move forward
- note - since we are playing with the array current pick, which is being mutated and is pass by reference, when adding to the final result - we construct a deep copy using `result.add(new ArrayList<>(current))`

```java
class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(0, candidates, target, result, new ArrayList<>());
        return result;
    }

    private void combinationSum(int idx, int[] candidates, int target, List<List<Integer>> result, List<Integer> current) {

        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (idx == candidates.length) {
            return;
        }

        if (candidates[idx] <= target) {
            current.add(candidates[idx]);
            combinationSum(idx, candidates, target - candidates[idx], result, current);
            current.remove(current.size() - 1);
        }

        combinationSum(idx + 1, candidates, target, result, current);
    }
}
```
