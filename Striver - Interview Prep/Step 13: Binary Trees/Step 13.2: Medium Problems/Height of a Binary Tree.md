# Height of a Binary Tree

- https://leetcode.com/problems/maximum-depth-of-binary-tree/

```java
class Solution {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```
