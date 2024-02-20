# Boundary Traversal of Binary Tree

- https://www.codingninjas.com/studio/problems/boundary-traversal-of-binary-tree_790725
- add root
- get left boundary - use root.left otherwise both this and right boundary calls will add root
- add leaves - level order traversal might not work - refer first example in link
  - leaves can be on different levels
  - we will not be able to go from left to right when doing level order traversal if leaves are on different levels
- add right boundary - use root.right otherwise both this and left boundary calls will add root

```java
import java.util.List;
import java.util.ArrayList;

public class Solution {

    public static List<Integer> traverseBoundary(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        
        if (root == null) return result;
        
        result.add(root.data);
        addLeftBoundary(root.left, result);
        addBottomBoundary(root, result);
        addRightBoundary(root.right, result);
        
        return result;
    }

    private static void addBottomBoundary(TreeNode root, List<Integer> result) {

        if (root == null) return;

        if (root.left == null && root.right == null) {
            // System.out.println("bottom: " + root.data);
            result.add(root.data);
        } else {
            addBottomBoundary(root.left, result);
            addBottomBoundary(root.right, result);
        }
    }

    private static void addLeftBoundary(TreeNode root, List<Integer> result) {

        if (root == null) return;

        if (root.left != null) {
            // System.out.println("left: " + root.data);
            result.add(root.data);
            addLeftBoundary(root.left, result);
        } else if (root.right != null) {
            // System.out.println("left: " + root.data);
            result.add(root.data);
            addLeftBoundary(root.right, result);
        }
    }

    private static void addRightBoundary(TreeNode root, List<Integer> result) {

        if (root == null) return;

        if (root.right != null) {
            // System.out.println("right: " + root.data);
            addRightBoundary(root.right, result);
            result.add(root.data);
        } else if (root.left != null) {
            // System.out.println("right: " + root.data);
            addRightBoundary(root.left, result);
            result.add(root.data);
        }
    }
}
```
