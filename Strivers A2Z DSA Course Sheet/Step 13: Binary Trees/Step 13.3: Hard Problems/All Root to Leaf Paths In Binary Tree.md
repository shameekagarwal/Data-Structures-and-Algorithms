# All Root to Leaf Paths In Binary Tree

- https://www.codingninjas.com/studio/problems/all-root-to-leaf-paths-in-binary-tree._983599
- this solution can be modified to be used for variants as well - e.g. find root to node x path
- note around mistakes i made - was adding to result when root == null
- but this will add duplicates - since both its left and right be null for leaf node
- so, check leaf node condition instead i.e. add path to result if root is lear node and not null

```java
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Solution {

    public static List<String> allRootToLeaf(BinaryTreeNode root) {
        
        List<String> serializedResult = new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        allRootToLeaf(root, current, result);

        for (List<Integer> path : result) {
            String s = path.stream().map(i -> i.toString()).collect(Collectors.joining(" "));
            serializedResult.add(s);
        }

        return serializedResult;
    }

    private static void allRootToLeaf(BinaryTreeNode root, List<Integer> current, List<List<Integer>> result) {
        
        if (root == null) return;
        current.add(root.data);

        if (root.left == null && root.right == null) {
            result.add(new ArrayList<>(current));
        }

        allRootToLeaf(root.left, current, result);
        allRootToLeaf(root.right, current, result);

        current.remove(current.size() - 1);
    }
}
``` 
