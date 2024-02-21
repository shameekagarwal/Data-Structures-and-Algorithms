# Time To Burn Tree

- https://www.codingninjas.com/studio/problems/time-to-burn-tree_1469067
- exactly like [All Nodes Distance K in Binary Tree](./All%20Nodes%20Distance%20K%20in%20Binary%20Tree.md), just the ask is different
- here, value is not given, not address of node, so i performed a search first 

```java
import java.util.*;

public class Solution {

    public static int timeToBurnTree(BinaryTreeNode < Integer > root, int start) {

        if (root == null) return 0;

        Map < BinaryTreeNode < Integer > , BinaryTreeNode < Integer >> parentPointers = populateParentPointers(root);
        BinaryTreeNode < Integer > target = searchNode(root, start);

        Deque < BinaryTreeNode < Integer >> queue = new ArrayDeque < > ();
        Set < BinaryTreeNode < Integer >> visited = new HashSet < > ();
        queue.addLast(target);
        visited.add(target);
        int currentDistance = 0;

        while (!queue.isEmpty()) {

            currentDistance += 1;
            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {

                BinaryTreeNode < Integer > node = queue.removeFirst();

                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.addLast(node.left);
                }

                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.addLast(node.right);
                }

                if (parentPointers.containsKey(node) && !visited.contains(parentPointers.get(node))) {
                    visited.add(parentPointers.get(node));
                    queue.addLast(parentPointers.get(node));
                }
            }
        }

        return currentDistance - 1;

    }

    private static BinaryTreeNode < Integer > searchNode(BinaryTreeNode < Integer > root, int value) {

        if (root == null) return null;

        if (root.data == value) return root;

        BinaryTreeNode < Integer > leftResult = searchNode(root.left, value);
        if (leftResult != null) return leftResult;

        BinaryTreeNode < Integer > rightResult = searchNode(root.right, value);
        if (rightResult != null) return rightResult;

        return null;
    }

    private static Map < BinaryTreeNode < Integer > , BinaryTreeNode < Integer >> populateParentPointers(BinaryTreeNode < Integer > root) {

        Map < BinaryTreeNode < Integer > , BinaryTreeNode < Integer >> parentPointers = new HashMap < > ();

        Deque < BinaryTreeNode < Integer >> queue = new ArrayDeque < > ();
        queue.addLast(root);

        while (!queue.isEmpty()) {

            BinaryTreeNode < Integer > parent = queue.removeFirst();

            if (parent.left != null) {
                parentPointers.put(parent.left, parent);
                queue.addLast(parent.left);
            }

            if (parent.right != null) {
                parentPointers.put(parent.right, parent);
                queue.addLast(parent.right);
            }
        }

        return parentPointers;
    }
}
```
