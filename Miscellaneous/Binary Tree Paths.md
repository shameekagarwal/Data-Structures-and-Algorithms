# Binary Tree Paths

- https://leetcode.com/problems/binary-tree-paths/

```java
class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        binaryTreePaths(root, new ArrayList<>(), result);
        return result;
    }

    public void binaryTreePaths(TreeNode root, List<TreeNode> current, List<String> result) {

        if (root == null) {
            return;
        }

        current.add(root);

        if (root.left == null && root.right == null) {

            String serialized = current.stream()
                .map((node) -> Integer.toString(node.val))
                .collect(Collectors.joining("->"));

            result.add(serialized);
        } else {
            binaryTreePaths(root.left, current, result);
            binaryTreePaths(root.right, current, result);
        }

        current.remove(current.size() - 1);
    }
}
```
