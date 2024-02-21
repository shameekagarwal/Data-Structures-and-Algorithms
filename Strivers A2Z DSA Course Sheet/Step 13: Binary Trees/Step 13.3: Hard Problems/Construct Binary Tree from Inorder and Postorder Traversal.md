# Construct Binary Tree from Inorder and Postorder Traversal

- https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
- solved it exactly like [Construct Binary Tree from Preorder and Inorder Traversal](./Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal.md)
- only difference - root at end of post order

```java
class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        Map<Integer, Integer> inorderIdxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIdxMap.put(inorder[i], i);
        }

        return buildTree(inorder, postorder, inorderIdxMap, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, Map<Integer, Integer> inorderIdxMap, int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {

        if (inorderStart > inorderEnd || postorderStart > postorderEnd) return null;

        TreeNode root = new TreeNode(postorder[postorderEnd]);
        int inorderRootIdx = inorderIdxMap.get(root.val);

        int inorderStartLeft = inorderStart;
        int inorderEndLeft = inorderRootIdx - 1;
        int inorderStartRight = inorderRootIdx + 1;
        int inorderEndRight = inorderEnd;

        int numberOfNodesOnLeft = inorderEndLeft - inorderStartLeft + 1;
        int postorderStartLeft = postorderStart;
        int postorderEndLeft = postorderStart + numberOfNodesOnLeft - 1;
        int postorderStartRight = postorderEndLeft + 1;
        int postorderEndRight = postorderEnd - 1;

        root.left = buildTree(inorder, postorder, inorderIdxMap, inorderStartLeft, inorderEndLeft, postorderStartLeft, postorderEndLeft);
        root.right = buildTree(inorder, postorder, inorderIdxMap, inorderStartRight, inorderEndRight, postorderStartRight, postorderEndRight);

        return root;
    }
}
```
