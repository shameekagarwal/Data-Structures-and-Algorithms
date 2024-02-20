# Iterative Postorder Traversal of Binary Tree.md

- https://leetcode.com/problems/binary-tree-postorder-traversal/
- time complexity - O(2N)
- code is almost same as [Iterative Preorder Traversal of Binary Tree](./Iterative%20Preorder%20Traversal%20of%20Binary%20Tree.md)
- here, push left then right though - because we know we are going to reverse the result at the end anyway

```java
class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return result;
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.removeLast();
            result.add(currentNode.val);
            if (currentNode.left != null) stack.addLast(currentNode.left);
            if (currentNode.right != null) stack.addLast(currentNode.right);
        }
        Collections.reverse(result);
        
        return result;
    }
}
```

- there is another solution which does not require this reversal of list
- time complexity is still O(2*N) when using this approach
- skipping it for now
