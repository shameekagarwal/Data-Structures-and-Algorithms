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
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {

    public static List<List<Integer>> getTreeTraversal(TreeNode root) {

        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();
        List<List<Integer>> result = List.of(inorder, preorder, postorder);
        Deque<StackNode> stack = new ArrayDeque<>();

        TreeNode current = root;

        while (true) {
            if (current != null) {
                preorder.add(current.data);
                stack.addLast(new StackNode(current, 1));
                current = current.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                } else if (stack.peekLast().tag == 1) {
                    StackNode top = stack.peekLast();
                    inorder.add(top.node.data);
                    top.tag = 2;
                    current = top.node.right;
                } else {
                    postorder.add(stack.removeLast().node.data);
                }
            }
        }

        return result;
    }

    static class StackNode {

        TreeNode node;
        int tag;

        StackNode(TreeNode node, int tag) {
            this.node = node; 
            this.tag = tag;
        }
    }
}
```
