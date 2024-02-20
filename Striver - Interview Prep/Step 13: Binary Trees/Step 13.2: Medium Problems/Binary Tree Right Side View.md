# Binary Tree Right Side View

- https://leetcode.com/problems/binary-tree-right-side-view/

## Approach 1 - Level Order Traversal

- last node of every level will be a part of our answer

```java
class Solution {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return result;
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode current = queue.removeFirst();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
                if (i == currentLevelSize - 1) {
                    result.add(current.val);
                }
            }
        }

        return result;
    }
}
```

## Approach 2 - Pre Order Traversal (Reverse)

- we go root -> right -> left (pre order is root -> left -> right)
- "every time the node is the first one encountered in the level" - it will be part of our answer
- to check this, just compare with size of result, because we are going top to bottom
- space complexity comparison with approach 1
  - here, stack space is used (max height of tree)
  - there, queue was used (max number of nodes in a level)

```java
class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recurse(result, root, 0);
        return result;
    }

    private void recurse(List<Integer> result, TreeNode root, int level) {

        if (root == null) return;
        if (result.size() == level) result.add(root.val);
        recurse(result, root.right, level + 1);
        recurse(result, root.left, level + 1);
    }
}
```
