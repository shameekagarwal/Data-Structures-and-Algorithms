# Symmetric Tree

- https://leetcode.com/problems/symmetric-tree/

```java
class Solution {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return (left == null) && (right == null);
        }
        return left.val == right.val &&
            isSymmetric(left.left, right.right) && 
            isSymmetric(left.right, right.left); 
    }
}
```
