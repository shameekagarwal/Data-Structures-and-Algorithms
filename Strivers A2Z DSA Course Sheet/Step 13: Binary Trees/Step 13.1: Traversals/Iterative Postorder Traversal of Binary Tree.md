# Iterative Postorder Traversal of Binary Tree.md

- https://leetcode.com/problems/binary-tree-postorder-traversal/

## Solution 1

- an intuitive solution i thought of
- easy to explain in interviews
- if current is not null - push current, false to stack, and set current to current.left
- if right of stack top is not visited, do not pop - set current to right of stack top right and mark its right visited as true
- if right of stack top is visited, add to result - but do not change current i.e. current stays null

```java
class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        
        Deque<StackNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        List<Integer> result = new ArrayList<>();

        while (true) {
            if (current != null) {
                stack.addLast(new StackNode(current, false));
                current = current.left;
            } else {
                if (stack.isEmpty()) break;
                if (stack.peekLast().rightVis) {
                    result.add(stack.removeLast().node.val);
                } else {
                    current = stack.peekLast().node.right;
                    stack.peekLast().rightVis = true;
                }
            }
        }

        return result;
    }

    static class StackNode {

        TreeNode node;
        boolean rightVis;

        StackNode(TreeNode node, boolean rightVis) {
            this.node = node;
            this.rightVis = rightVis;
        }

        @Override
        public String toString() {
            return "(" + node.val + ", " + rightVis + ")"; 
        }
    }
}
```

## Strivers Solution

- time complexity - O(2N)
- code is almost same as [Iterative Preorder Traversal of Binary Tree](./Iterative%20Preorder%20Traversal%20of%20Binary%20Tree.md)
- here, push left then right though - because we know we are going to reverse the result at the end anyway
- if question does not allow reversal - instead of adding to final result list, add to an auxiliary stack - this way, when popping off of stack, we would have the result

```java
class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        List<Integer> result = new ArrayList<>();

        while (true) {
            if (current != null) {
                result.add(current.val);
                stack.addLast(current);
                current = current.right;
            } else {
                if (stack.isEmpty()) break;
                current = stack.removeLast();
                current = current.left;
            }
        }

        Collections.reverse(result);

        return result;
    }
}
```
