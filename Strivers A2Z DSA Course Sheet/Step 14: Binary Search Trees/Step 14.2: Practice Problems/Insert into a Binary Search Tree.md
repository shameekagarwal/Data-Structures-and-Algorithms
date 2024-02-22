# Insert into a Binary Search Tree

- https://leetcode.com/problems/insert-into-a-binary-search-tree/
- question already says no duplicates / value to insert does not exist
- so, we just try to find the leaf where we can put it
- if value to insert is smaller than the current node
  - if left is null, put on left
  - else move current to left
- if value to insert is greater than the current node
  - if right is null, put on right
  - else move current to right
- complexity - log2N (for height balanced)

```java
class Solution {

    public TreeNode insertIntoBST(TreeNode root, int val) {

        TreeNode newNode = new TreeNode(val);
        if (root == null) return newNode;
        TreeNode current = root;

        while (true) {
            if (current.val > newNode.val) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                } else {
                    current = current.right;
                }
            }
        }

        return root;
    }
}
```
