# Iterative Preorder Traversal of Binary Tree

- https://leetcode.com/problems/binary-tree-preorder-traversal/
- use a stack
- push root
- then repeat the steps below - 
- pop from stack
- because stack is lifo, first push right then left, so that left is popped first
- remember the null check for root i.e. empty tree

```java
class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.removeLast();
            result.add(currentNode.val);
            if (currentNode.right != null) stack.addLast(currentNode.right);
            if (currentNode.left != null) stack.addLast(currentNode.left);
        }

        return result;
    }
}
```
