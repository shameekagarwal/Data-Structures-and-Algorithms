# House Robber III

- https://leetcode.com/problems/house-robber-iii/

```java
class Solution {

    public int rob(TreeNode root) {
        State state = _rob(root);
        return Math.max(state.include, state.exclude);
    }

    private State _rob(TreeNode root) {
        
        if (root == null) {
            return new State(0, 0);
        }

        State leftState = _rob(root.left);
        State rightState = _rob(root.right);

        int includeScore = leftState.exclude + rightState.exclude + root.val;
        int excludeScore = leftState.include + rightState.include;

        return new State(
            Math.max(includeScore, excludeScore),
            excludeScore
        );
    }

    static class State {

        int include;
        int exclude;

        State(int include, int exclude) {
            this.include = include;
            this.exclude = exclude;
        }
    }
}
```
