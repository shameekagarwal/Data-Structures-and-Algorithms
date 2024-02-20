# Binary Tree Preorder Traversal

- https://leetcode.com/problems/binary-tree-preorder-traversal/
- time complexity - O(N)
- space complexity - auxiliary O(N) where N is space required by stack

```java
class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(result, root);
        return result;
    }

    private void preorderTraversal(List<Integer> result, TreeNode root) {

        if (root == null) return;

        result.add(root.val);
        preorderTraversal(result, root.left);
        preorderTraversal(result, root.right);
    }
}
```
