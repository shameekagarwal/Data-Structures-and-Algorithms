# Iterative Inorder Traversal of Binary Tree

- https://leetcode.com/problems/binary-tree-inorder-traversal/
- keep pushing left while possible
- once current node becomes null, pop off top of stack, print it and then make current node as right of popped off node
- this ensures inorder traversal automatically
- remember the code - it is not straightforward to come up with

```java
class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode currentNode = root;
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        while(true) {
            if (currentNode != null) {
                stack.add(currentNode);
                currentNode = currentNode.left;
            } else {
                if (stack.isEmpty()) break;
                result.add(stack.peekLast().val);
                currentNode = stack.removeLast().right;
            }
        }

        return result;
    }
}
```
