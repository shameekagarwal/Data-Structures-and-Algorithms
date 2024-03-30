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

    private TreeNode currentNextNode;
    private Deque<TreeNode> nextStack;

    private TreeNode currentPreviousNode;
    private Deque<TreeNode> previousStack;

    public boolean findTarget(TreeNode root, int k) {

        currentNextNode = root;
        nextStack = new ArrayDeque<>();

        currentPreviousNode = root;
        previousStack = new ArrayDeque<>();

        int l = next();
        int r = previous();

        while (true) {
            if (l == r) {
                return false;
            } else if (l + r > k) {
                if (!hasPrevious()) return false;
                r = previous();
            } else if (l + r < k) {
                if (!hasNext()) return false;
                l = next();
            } else {
                return true;
            }
        }
    }

    private int next() {
        while (currentNextNode != null) {
            nextStack.addLast(currentNextNode);
            currentNextNode = currentNextNode.left;
        }
        int value = nextStack.peekLast().val;
        currentNextNode = nextStack.removeLast().right;
        return value;
    }

    private int previous() {
        while (currentPreviousNode != null) {
            previousStack.addLast(currentPreviousNode);
            currentPreviousNode = currentPreviousNode.right;
        }
        int value = previousStack.peekLast().val;
        currentPreviousNode = previousStack.removeLast().left;
        return value;
    }

    private boolean hasNext() {
        return !(currentNextNode == null && nextStack.isEmpty());
    }

    private boolean hasPrevious() {
        return !(currentPreviousNode == null && previousStack.isEmpty());
    }
}
```
