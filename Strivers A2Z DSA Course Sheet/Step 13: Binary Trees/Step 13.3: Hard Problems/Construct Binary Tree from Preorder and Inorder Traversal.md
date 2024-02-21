# Construct Binary Tree from Preorder and Inorder Traversal

- https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
- talk about the intuition described [here](./Requirements%20Needed%20to%20Construct%20a%20Unique%20Binary%20Tree.md)
- root = first node in pre order
- now find this root in in order
- for in order - 
  - left subtree - (in order start) to (in order root idx - 1)
  - right subtree - (in order root idx + 1) to (in order end)
- now, number of nodes in left can be calculated from above
- now, for pre order - 
  - left subtree - (pre order start + 1) to (pre order start + number of nodes in left - 1)
  - right subtree - (end of above + 1) to (pre order end)
- solve recursively
- to get index of in order given the value, using hash map
- note the base case for null - invalid ranges

```java
class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        Map<Integer, Integer> inorderIdxMap = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++) {
            inorderIdxMap.put(inorder[i], i);
        }

        return buildTree(preorder, inorder, inorderIdxMap, 0, inorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, Map<Integer, Integer> inorderIdxMap, 
        int inorderStart, int inorderEnd, int preorderStart, int preorderEnd) {

        if (inorderStart > inorderEnd || preorderStart > preorderEnd) return null;

        TreeNode root = new TreeNode(preorder[preorderStart]);
        int inorderRootIdx = inorderIdxMap.get(root.val);

        int leftInorderStart = inorderStart;
        int leftInorderEnd = inorderRootIdx - 1;
        int rightInorderStart = inorderRootIdx + 1;
        int rightInorderEnd = inorderEnd;

        int numberOfNodesOnLeft = leftInorderEnd - leftInorderStart + 1;
        int leftPreorderStart = preorderStart + 1;
        int leftPreorderEnd = leftPreorderStart + numberOfNodesOnLeft - 1;
        int rightPreorderStart = leftPreorderEnd + 1;
        int rightPreorderEnd = preorderEnd;

        root.left = buildTree(preorder, inorder, inorderIdxMap, leftInorderStart, leftInorderEnd, leftPreorderStart, leftPreorderEnd);
        root.right = buildTree(preorder, inorder, inorderIdxMap, rightInorderStart, rightInorderEnd, rightPreorderStart, rightPreorderEnd);

        return root;
    }
}
```
