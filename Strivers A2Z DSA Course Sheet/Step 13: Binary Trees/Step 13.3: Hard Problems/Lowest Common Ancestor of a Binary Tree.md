# Lowest Common Ancestor of a Binary Tree

- https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
- have modelled separate cases

```java
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        // example 2 in leetcode
        // current node is lca, and its left / right has one of the two values
        if (root == p && (leftResult != null || rightResult != null)) return root;
        if (root == q && (leftResult != null || rightResult != null)) return root;

        // current node has one of the two values
        if ((root == p) || (root == q)) return root;

        // example 1 in leetcode
        // both left and right have one of the two values
        if ((leftResult != null) && (rightResult != null)) return root;

        // either left or right has one of the two values
        if (leftResult != null) return leftResult;
        if (rightResult != null) return rightResult;

        return null;
    }
}
```
