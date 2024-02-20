# Combination Sum III

- https://leetcode.com/problems/combination-sum-iii/
- base cases include k reach 0, digit reaches 10
- add to result if current numbers picked equal k and target has become 0

```java
class Solution {
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        recurse(result, k, n, 1, new ArrayList<>());
        return result;
    }

    private void recurse(List<List<Integer>> result, int k, int n, int digit, List<Integer> pick) {

        if (k == 0) {
            if (n == 0) {
                result.add(new ArrayList<>(pick));
            }
            return;
        }
        if (digit == 10) return;

        if (digit <= n) {
            pick.add(digit);
            recurse(result, k - 1, n - digit, digit + 1, pick);
            pick.remove(pick.size() - 1);
        }
        recurse(result, k, n, digit + 1, pick);
    }
}
```
