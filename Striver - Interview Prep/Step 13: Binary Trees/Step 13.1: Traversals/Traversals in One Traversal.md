# Traversals in One Traversal

- https://www.codingninjas.com/studio/problems/tree-traversals_981269
- we maintain a count, node pair
- push root
- pop from stack
- if count is 1
  - include in pre order
  - push (popped, 2) and (popped.left, 1) - allows for lef to be traversed nex for pre order
- if count is 2
  - include in in order
  - push (popped, 3) and (popped.right, 1) - allows for right to be traversed next for pre order
- if count is 3
  - include in post order

```java
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution {

    public static List<List<Integer>> getTreeTraversal(TreeNode root) {

        Deque<StackNode> stack = new ArrayDeque<>();
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();
        List<List<Integer>> result = List.of(inorder, preorder, postorder);

        if (root == null) return result;

        stack.addLast(new StackNode(root, 1));

        while (!stack.isEmpty()) {
            StackNode stackNode = stack.removeLast();
            if (stackNode.count == 1) {
                preorder.add(stackNode.node.data);
                stack.addLast(new StackNode(stackNode.node, 2));
                if (stackNode.node.left != null) {
                    stack.addLast(new StackNode(stackNode.node.left, 1));
                }
            } else if (stackNode.count == 2) {
                inorder.add(stackNode.node.data);
                stack.addLast(new StackNode(stackNode.node, 3));
                if (stackNode.node.right != null) {
                    stack.addLast(new StackNode(stackNode.node.right, 1));
                }
            } else if (stackNode.count == 3) {
                postorder.add(stackNode.node.data);
            }
            // System.out.println("stack: " + stack);
            // System.out.println("preorder: " + preorder);
            // System.out.println("inorder: " + inorder);
            // System.out.println("postorder: " + postorder);
            // System.out.println("**********");
        }

        return result;
    }

    private static class StackNode {

        TreeNode node;
        int count;

        StackNode(TreeNode node, int count) {
            this.node = node;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(" + node.data + ": " + count + ")";
        }
    }
}
```
