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
        
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;

        while (true) {
            if (current != null) {
                result.add(current.val);
                stack.addLast(current);
                current = current.left;
            } else {
                if (stack.isEmpty()) break;
                current = stack.removeLast().right;
            }
        }

        return result;
    }
}
```
