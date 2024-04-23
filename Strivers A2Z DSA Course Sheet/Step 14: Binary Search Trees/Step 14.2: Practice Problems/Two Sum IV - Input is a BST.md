# Two Sum IV - Input is a BST

- https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
- brute force - construct in order, then use two pointer approach described [here](../../Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.2:%20Medium/Two%20Sum.md)
- time complexity - O(2N), space complexity - O(N)

```java
class Solution {

    private List<Integer> inorder;

    public boolean findTarget(TreeNode root, int k) {
        
        inorder = new ArrayList<>();
        computeInorder(root);
        
        int l = 0;
        int r = inorder.size() - 1;

        while (l < r) {
            if (inorder.get(l) + inorder.get(r) < k) l += 1;
            else if (inorder.get(l) + inorder.get(r) > k) r -= 1;
            else return true;
        }

        return false;
    }

    private void computeInorder(TreeNode root) {
        if (root == null) return;
        computeInorder(root.left);
        inorder.add(root.val);
        computeInorder(root.right);
    }
}
```

## Optimal

- we can have twp iterators - previous and next
- use [this](./Binary%20Search%20Tree%20Iterator.md) for next
- for previous, go right -> root -> left
- if asked in interview, maybe use separate classes for the two iterators, use an interface for the common structure

```java
class Solution {

    public boolean findTarget(TreeNode root, int k) {

        LeftIterator leftIterator = new LeftIterator(root);
        RightIterator rightIterator = new RightIterator(root);

        int left = leftIterator.next().val;
        int right = rightIterator.next().val;

        while (left < right) {
            
            if (left + right == k) {
                return true;
            } else if (left + right > k) {
                right = rightIterator.next().val;
            } else {
                left = leftIterator.next().val;
            }
        }

        return false;
    }

    static class LeftIterator {

        TreeNode current;
        Deque<TreeNode> stack;

        LeftIterator(TreeNode root) {
            this.current = root;
            stack = new ArrayDeque<>();
        }

        TreeNode next() {

            while (current != null) {
                stack.addLast(current);
                current = current.left;
            }

            TreeNode nextNode = stack.removeLast();
            current = nextNode.right;

            return nextNode;
        }
    }

    static class RightIterator {

        TreeNode current;
        Deque<TreeNode> stack;

        RightIterator(TreeNode root) {
            this.current = root;
            stack = new ArrayDeque<>();
        }

        TreeNode next() {

            while (current != null) {
                stack.addLast(current);
                current = current.right;
            }

            TreeNode nextNode = stack.removeLast();
            current = nextNode.left;

            return nextNode;
        }
    }
}
```
