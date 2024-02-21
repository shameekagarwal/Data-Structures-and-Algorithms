# All Root to Leaf Paths In Binary Tree

- https://www.codingninjas.com/studio/problems/all-root-to-leaf-paths-in-binary-tree._983599
- this solution can be modified to be used for variants as well - e.g. find root to node x path
- note around mistakes i made - was adding to result when root == null - will add duplicates for leaf nodes, since both its left and right will evaluate to null. so, check leaf node condition
- using StringBuilder for `currentPath` - removing one / adding one character works, but `node.data` can have multiple digits - so, using `StringBuilder` can complicate solution. so, use linked list instead

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {

    public static List<String> allRootToLeaf(BinaryTreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        allRootToLeaf(result, root, new ArrayDeque<>());
        return result;
    }

    private static void allRootToLeaf(List<String> result, BinaryTreeNode root, ArrayDeque<Integer> currentPath) {
        currentPath.addLast(root.data);
        if (root.left == null && root.right == null) {
            StringBuilder currentPathString = new StringBuilder();
            for (Integer nodeData : currentPath) {
                currentPathString.append(nodeData);
                currentPathString.append(' ');
            }
            result.add(currentPathString.toString());
        } else {
            if (root.left != null) {
                allRootToLeaf(result, root.left, currentPath);
            }
            if (root.right != null) {
                allRootToLeaf(result, root.right, currentPath);
            }
        }
        currentPath.removeLast();
    }
}
``` 
