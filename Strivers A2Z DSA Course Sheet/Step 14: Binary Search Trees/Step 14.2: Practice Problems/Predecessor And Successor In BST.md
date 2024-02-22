# Predecessor And Successor In BST

- https://www.codingninjas.com/studio/problems/predecessor-and-successor-in-bst_893049
- binary search after constructing in order
- this is O(N) to store, O(logN) to search
- better - while performing the in order traversal, find it
- complexity - O(N) if tree skewed, O(2 * logN) if balanced

```java
import java.util.List;

public class Solution {

    public static List<Integer> predecessorSuccessor(TreeNode root, int key) {
        List<Integer> result = List.of(-1, -1);
        predecessor(root, key, result);
        successor(root, key, result);
        return result;
    }

    private static void predecessor(TreeNode root, int key, List<Integer> result) {
        if (root == null) return;
        if (root.data < key) {
            result.set(0, root.data);
            predecessor(root.right, key, result);
        } else {
            predecessor(root.left, key, result);
        }
    }

    private static void successor(TreeNode root, int key, List<Integer> result) {
        if (root == null) return;
        if (root.data > key) {
            result.set(1, root.data);
            successor(root.left, key, result);
        } else {
            successor(root.right, key, result);
        }
    }
}
```
