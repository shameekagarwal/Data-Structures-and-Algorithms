# Binary Tree Postorder Traversal

- https://leetcode.com/problems/binary-tree-postorder-traversal/

```java
class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversal(result, root);
        return result;
    }

    private void postorderTraversal(List<Integer> result, TreeNode root) {

        if (root == null) return;

        postorderTraversal(result, root.left);
        postorderTraversal(result, root.right);
        result.add(root.val);
    }
}
```
