# Search in a Binary Search Tree

- https://leetcode.com/problems/search-in-a-binary-search-tree/
- in a normal bt, we would have done any of the three dfs / bfs, thus taking O(N)
- we know left is smaller, right is bigger
- so based on what comparison with current node yields, we either go left or go right
- time complexity - O(logN) - we travel only the height of the tree

```java
class Solution {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        else if (root.val == val) return root;
        else if (root.val < val) return searchBST(root.right, val);
        else return searchBST(root.left, val);
    }
}
```
