# Delete Node in a BST

- https://leetcode.com/problems/delete-node-in-a-bst/
- node can be present or not present
- handle if root needs to be deleted separately, since new root needs to be returned as well
- check if current nodes left or right needs to be deleted
- adjust node to delete - 
  - make left of node to delete as new root
  - put right of node to delete as the rightmost element of left of node to delete
  - edge case - there is no left of node to delete - make right of node to delete as new root
- otherwise, continue traversing the tree

```java
class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) return null;

        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else {
                insertRightMost(root.left, root.right);
                return root.left;
            }
        }

        TreeNode current = root;

        while (current != null) {
            if (current.right != null && current.right.val == key) {
                current.right = adjustNodeToDelete(current.right);
                break;
            } else if (current.left != null && current.left.val == key) {
                current.left = adjustNodeToDelete(current.left);
                break;
            } else if (current.val > key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return root;
    }

    private TreeNode adjustNodeToDelete(TreeNode nodeToDelete) {
        if (nodeToDelete.left == null) {
            return nodeToDelete.right;
        } else {
            insertRightMost(nodeToDelete.left, nodeToDelete.right);
            return nodeToDelete.left;
        }
    }

    private void insertRightMost(TreeNode root, TreeNode nodeToInsert) {
        while (root.right != null) {
            root = root.right;
        }
        root.right = nodeToInsert;
    }
}
```
