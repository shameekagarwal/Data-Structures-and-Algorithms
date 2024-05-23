# Frog Jump

- https://leetcode.com/problems/frog-jump/
- state of recursion - index, previousJumpLength
- we can jump to - 
  - previousJumpLength - 1
  - previousJumpLength
  - previousJumpLength + 1
- ignore the jump if it is same / negative - cannot jump 0 to 0 (never ending cycle) or 0 to -1 (jumping backwards)
- now, we can make this next jump only if `stones[idx] + jumpLength` is in the array
- if it is not, we cannot make this jump
- new state `index_of_(stones[idx] + jumpLength)`, jumpLength

```java
class Solution {

    public boolean canCross(int[] stones) {

        int n = stones.length;

        Map<Integer, Integer> lookup = generateLookup(stones);

        boolean[][] dp = new boolean[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];
    
        return recurse(lookup, stones, 0, 0, dp, visited);
    }

    private Map<Integer, Integer> generateLookup(int[] arr) {

        Map<Integer, Integer> lookup = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            lookup.put(arr[i], i);
        }

        return lookup;
    }

    private boolean recurse(Map<Integer, Integer> lookup, int[] stones, 
      int idx, int previousJumpLength, boolean[][] dp, boolean[][] visited) {

        if (idx > stones.length) {
            return false;
        }

        if (idx == stones.length - 1) {
            return true;
        }

        if (visited[idx][previousJumpLength]) {
            return dp[idx][previousJumpLength];
        }

        boolean result = false;

        for (int jumpLength : List.of(previousJumpLength - 1, previousJumpLength, previousJumpLength + 1)) {

            if (jumpLength <= 0) continue;

            if (lookup.containsKey(stones[idx] + jumpLength)) {
                result |= recurse(lookup, stones, lookup.get(stones[idx] + jumpLength), jumpLength, dp, visited);
            }
        }

        visited[idx][previousJumpLength] = true;
        dp[idx][previousJumpLength] = result;

        return result;
    }
}
```
