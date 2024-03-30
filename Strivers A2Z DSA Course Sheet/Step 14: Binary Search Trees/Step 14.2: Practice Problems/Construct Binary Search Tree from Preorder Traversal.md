# Construct Binary Search Tree from Preorder Traversal

## Naive - Solution 1

- create root from 0th index
- call [Insert into a Binary Search Tree](./Insert%20into%20a%20Binary%20Search%20Tree.md) by iterating through the array
- time complexity - O(N^logN) - every insertion should take logN (if balanced) but since we do not take care of balancing, can be O(N^2)

```java
class Solution {

    public TreeNode bstFromPreorder(int[] preorder) {
        
        int n = preorder.length;

        if (n == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < n; i++) {
            
            TreeNode newNode = new TreeNode(preorder[i]);

            TreeNode current = root;

            while (true) {
                if (current.val > newNode.val) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    }
                    current = current.right;
                }
            }
        }

        return root;
    }
}
```

## Naive - Solution 2

- we can also sort the pre order traversal
- now, sorted pre order = in order (recall in order of bst is sorted)
- now, we have already seen we can [construct a unique binary tree from a given pre order and in order](../../Step%2013:%20Binary%20Trees/Step%2013.3:%20Hard%20Problems/Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal.md)
- so this way, total complexity comes out to be O(N*logN) + O(N)

## Solution 3

- optimal - we traverse the entire pre order serially
- complexity - O(N)
- we keep changing bounds depending on whether we go left or right
- imp - if bounds exceed current node's value - return null

```java
class Solution {

    private int currentIdx;
    private int[] preorder;

    public TreeNode bstFromPreorder(int[] preorder) {
        currentIdx = 0;
        this.preorder = preorder;
        return bstFromPreorder(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode bstFromPreorder(int minValue, int maxValue) {
        
        if (currentIdx >= preorder.length) return null;
        if (preorder[currentIdx] < minValue || preorder[currentIdx] > maxValue) return null;

        TreeNode current = new TreeNode(preorder[currentIdx]);
        currentIdx += 1;
        current.left = bstFromPreorder(minValue, current.val);
        current.right = bstFromPreorder(current.val, maxValue);
        return current;
    }
}
```
