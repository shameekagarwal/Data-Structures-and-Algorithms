# Kth Smallest Element in a BST

- https://leetcode.com/problems/kth-smallest-element-in-a-bst/
- bst in order is in sorted order
- so first, calculate size of binary tree
- if question was kth largest - find n - kth smallest
- doubt ror above - i think we can also change to do reverse in order instead for kth largest?

```java
class Solution {

    private TreeNode kthSmallestNode;
    private int k;
    
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        _kthSmallest(root);
        return kthSmallestNode.val;
    }

    public void _kthSmallest(TreeNode root) {
        if (root == null) return;
        _kthSmallest(root.left);
        if (k == 1) kthSmallestNode = root;
        k -= 1;
        _kthSmallest(root.right);
    }
}
```
