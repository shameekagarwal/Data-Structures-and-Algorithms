# Combination Sum

- https://leetcode.com/problems/combination-sum/
- two recursive calls
  - use the current element and stay at current index
  - do not use the current element but move forward
- base cases would be target = current element / index reaching array length
- understand how both these cases include using an element any x number of times till sum is less than target
- note - while writing this, i added the recursive call - "use the current element and increment the index" (which is commented now), which was resulting in duplicates
- this is because `... target - currentIdx, currentIdx + 1 ...` is covered by - 
  - call 1 - `... target - currentIdx, currentIdx ...` - "use the current element and do not increment the index"
  - call 2 - `... target, currentIdx + 1 ...` - "do not use the current element and increment the index"
- another note - since we are playing with the array current pick, which is being mutated and is pass by reference, when adding to the final result - we construct a deep copy using `result.add(new ArrayList<>(currentPick))`

```java
class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        recurse(result, candidates, target, 0, new ArrayList<>());
        return result;
    }

    private void recurse(List<List<Integer>> result, int[] candidates, int target, int currentIdx, List<Integer> currentPick) {

        if (currentIdx == candidates.length) {
            return;
        }

        if (candidates[currentIdx] == target) {
            currentPick.add(candidates[currentIdx]);
            result.add(new ArrayList<>(currentPick));
            currentPick.remove(currentPick.size() - 1);
        } else if (candidates[currentIdx] < target) {
            currentPick.add(candidates[currentIdx]);
            recurse(result, candidates, target - candidates[currentIdx], currentIdx, currentPick);
            // recurse(result, candidates, target - candidates[currentIdx], currentIdx + 1, currentPick);
            currentPick.remove(currentPick.size() - 1);
        }

        recurse(result, candidates, target, currentIdx + 1, currentPick);
    }
}
```
