# Lowest Common Ancestor of a Binary Search Tree

- https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
- naive- [Lowest Common Ancestor](../../Step%2013:%20Binary%20Trees/Step%2013.3:%20Hard%20Problems/Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree.md)
- time complexity - O(N) - all nodes need to be traversed
- optimal - O(logN) - only height of tree needs o be traversed. at every state, we will know whether to go left or right
- if current node has one of the values - it has to be lca - since we are going top to bottom
- if one should be on right (i.e. greater), while other should be on left (i.e. lesser), current node has to be lca
- if smaller value of the two values is also greater - both should be on right
- if greater value of the two values is also smaller - both should on left

```java
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        if (p.val > q.val) return lowestCommonAncestor(root, q, p);

        // root is lca and has a value
        else if (root.val == p.val || root.val == q.val) return root;

        // root is lca
        else if (root.val > p.val && root.val < q.val) return root;

        // root value is lesser that the lesser value - lca is on right
        else if (root.val < p.val) return lowestCommonAncestor(root.right, p, q);

        else if (root.val > p.val) return lowestCommonAncestor(root.left, p, q);

        return null;
    }
}
```
