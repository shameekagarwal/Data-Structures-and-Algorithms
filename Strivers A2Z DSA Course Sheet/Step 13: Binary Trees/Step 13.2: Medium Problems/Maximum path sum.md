# Maximum path sum

- https://leetcode.com/problems/binary-tree-maximum-path-sum/
- we use left and right contribution to handle -ve - no point including a negative number when calculating max
- there are two factors
  - max seen till now - `max(max seen till now, current value + left contribution + right contribution)`
  - max linear path sum till current node - `current value + max(right contribution, left contribution)`
- note - remember to return integer.min_value and not 0 as the base case - we want non empty paths

```java
class Solution {

    public int maxPathSum(TreeNode root) {
        return _maxPathSum(root).maxTillNow;
    }

    private MaxSum _maxPathSum(TreeNode root) {

        if (root == null) return new MaxSum(0, Integer.MIN_VALUE);

        MaxSum leftMaxSum = _maxPathSum(root.left);
        MaxSum rightMaxSum = _maxPathSum(root.right);

        int leftContribution = Math.max(0, leftMaxSum.maxLinearPathSum);
        int rightContribution = Math.max(0, rightMaxSum.maxLinearPathSum);

        int maxSumIncludingCurrent = root.val + leftContribution + rightContribution;
        int maxLinearPathSum = root.val + Math.max(leftContribution, rightContribution);

        return new MaxSum(maxLinearPathSum, Math.max(maxSumIncludingCurrent, Math.max(leftMaxSum.maxTillNow, rightMaxSum.maxTillNow)));
    }

    static class MaxSum {

        int maxLinearPathSum;
        int maxTillNow;

        MaxSum(int maxLinearPathSum, int maxTillNow) {
            this.maxLinearPathSum = maxLinearPathSum;
            this.maxTillNow = maxTillNow;
        }
    }
}
```
