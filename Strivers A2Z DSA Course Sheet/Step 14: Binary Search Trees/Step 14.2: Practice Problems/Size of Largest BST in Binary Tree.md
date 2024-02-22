# Size of Largest BST in Binary Tree

- https://www.codingninjas.com/studio/problems/size-of-largest-bst-in-binary-tree_893103
- brute - [perform check](./Validate%20Binary%20Search%20Tree.md) for every node
- time complexity of this brute solution - O(N^2)
- optimal - bottom up approach
- using current node as root, we have a valid bst if -
  - left is a valid bst
  - right is a valid bst
  - current node is greater than largest from left and smaller than smallest from right
- so, size becomes size of left + size of right + 1
- for null
  - we pass int max as min value, so that when compared, value is lesser than it
  - similarly, we pass int min as max value
- we pass min of its value, min from left for min of bst ending at current node
- similarly, we pass max of its value, max from right for max of bst ending at current node
- if bst is invalid, we pass int min as min value and int max as max value
- this way, if it is on left, nothing above can have value greater than its max
- similarly, if it is on right, nothing above it can have value lesser than its min

```java
public class Solution {

    private static int largestBSTNodeCount;

    public static int largestBST(TreeNode root) {
        largestBSTNodeCount = 0;
        _largestBST(root);
        return largestBSTNodeCount;
    }

    private static ValidateBST _largestBST(TreeNode root) {

        if (root == null) {
            return new ValidateBST(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        ValidateBST leftValidateBST = _largestBST(root.left);
        ValidateBST rightValidateBST = _largestBST(root.right);

        if (root.data > leftValidateBST.maxValue && root.data < rightValidateBST.minValue) {
            largestBSTNodeCount = Math.max(largestBSTNodeCount, leftValidateBST.size + rightValidateBST.size + 1);
            return new ValidateBST(
                Math.min(leftValidateBST.minValue, root.data),
                Math.max(rightValidateBST.maxValue, root.data),
                leftValidateBST.size + rightValidateBST.size + 1
            );
        }

        return new ValidateBST(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }

    private static class ValidateBST {

        int minValue;
        int maxValue;
        int size;

        ValidateBST(int minValue, int maxValue, int size) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.size = size;
        }

        @Override
        public String toString() {
            return "ValidateBST(minValue=" + minValue + ", maxValue=" + maxValue + ", size=" + size + ")";
        }
    }
}
```
