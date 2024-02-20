# Inorder Traversal of Binary Tree

- https://leetcode.com/problems/binary-tree-inorder-traversal/

```java
class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(result, root);
        return result;
    }

    private void inorderTraversal(List<Integer> result, TreeNode root) {

        if (root == null) return;

        inorderTraversal(result, root.left);
        result.add(root.val);
        inorderTraversal(result, root.right);
    }
}
```
