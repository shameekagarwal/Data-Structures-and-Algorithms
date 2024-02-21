# Count Complete Tree Nodes

- https://leetcode.com/problems/count-complete-tree-nodes/
- easy in O(n) using some traversal, space complexity - O(h) (auxiliary recursion stack space)
- number of nodes in a [perfect binary tree](../Step%2013.1:%20Traversals/Introduction%20to%20Trees.md) - 2^x - 1
- if leftmost height = rightmost height - current tree is a perfect binary tree
- refer implementation below to see what leftmost and rightmost heights mean
- if not - total number of nodes = number of nodes in left tree + number of nodes in right tree + 1
- now, finding height takes logN time
- and every time, we try calculate the height
- worst case - we keep on going down till the leaf, and keep on calculating remaining height every time
- so, O(logN * logN)
- i think actually, should be lesser than that - calculating height takes logN, then O(logN - 1) for level 2, O(logN - 2) for level 3 and so on - leaf height takes O(1) time to calculate

```java
class Solution {

    public int countNodes(TreeNode root) {
        
        int leftMostHeight = countLeftMostHeight(root);
        int rightMostHeight = countRightMostHeight(root);
        
        if (leftMostHeight == rightMostHeight) return (1 << leftMostHeight) - 1;

        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    
    private int countLeftMostHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + countLeftMostHeight(root.left);
    }

    private int countRightMostHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + countRightMostHeight(root.right);
    }
}
```
