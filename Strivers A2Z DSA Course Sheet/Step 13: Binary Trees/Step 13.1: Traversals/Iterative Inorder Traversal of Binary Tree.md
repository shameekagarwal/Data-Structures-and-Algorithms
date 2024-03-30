# Iterative Inorder Traversal of Binary Tree

- https://leetcode.com/problems/binary-tree-inorder-traversal/
- keep pushing left while possible
- once current node becomes null, pop off top of stack, print it and then make current node as right of popped off node
- this ensures inorder traversal automatically
- remember the code - it is not straightforward to come up with

```java
class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (true) {
            if (current != null) {
                stack.addLast(current);
                current = current.left;
            } else {
                if (stack.isEmpty()) break;
                current = stack.removeLast();
                result.add(current.val);
                current = current.right;
            }
        }

        return result;
    }
}
```
