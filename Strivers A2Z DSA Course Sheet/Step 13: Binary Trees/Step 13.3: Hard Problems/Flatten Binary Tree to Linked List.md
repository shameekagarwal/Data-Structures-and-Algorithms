# Flatten Binary Tree to Linked List

- https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

## Recursion

- we first travel right then left
- update last node seen to current node
- this way, every time we reach a node - we need to make its right to last seen node, and left to null
- if a node has a left - last seen node would be its left
- if a node does not have a left but has a right - last seen node would be its right
- if a node has neither left nor right - this tricky case is also handled by this recursion - the 4 to 5 linking in the leetcode link example

```java
class Solution {

    private TreeNode lastNodeSeen;

    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = lastNodeSeen;
        root.left = null;
        lastNodeSeen = root;
    }
}
```

## Another Possible Solution - previous

- every time we reach a node, we should know the previous node seen
- this way, previous's right can be pointed to the current node
- current node's left should be marked null and set previous to current node
- then, flatten left and then right

```java
class Solution {

    private TreeNode previous;

    public void flatten(TreeNode root) {
        previous = null;
        _flatten(root);
    }

    private void _flatten(TreeNode root) {

        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (previous != null) {
            previous.right = root;
        }

        root.left = null;
        previous = root;

        _flatten(left);
        _flatten(right);
    }
}
```

## Stack

- might be easier to explain

```java
class Solution {

    public void flatten(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;

        stack.addLast(root);

        while (!stack.isEmpty()) {

            TreeNode node = stack.removeLast();
            if (node.right != null) {
                stack.addLast(node.right);
            }
            if (node.left != null) {
                stack.addLast(node.left);
            }
            if (!stack.isEmpty()) {
                node.right = stack.peekLast();
                // System.out.printf("linking %d to %d\n", node.val, (node.right == null ? null : node.right.val));
            }
            node.left = null;
        }
    }
}
```
