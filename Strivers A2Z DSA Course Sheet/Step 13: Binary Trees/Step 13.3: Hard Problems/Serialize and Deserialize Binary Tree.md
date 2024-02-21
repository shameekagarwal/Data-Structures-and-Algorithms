# Serialize and Deserialize Binary Tree

- https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
- doing level order traversal, but we could have done for e.g. in order + (post order / pre order) as discussed [here](./Requirements%20Needed%20to%20Construct%20a%20Unique%20Binary%20Tree.md)
- apparently, we cannot add null to deque. reason - null is a special value it uses e.g. when poll is called on an empty deque. this is why, when serializing, i had to serialize when adding to queue and not when popping from queue (null cannot be added to it)
- e.g. in leetcode will give this string for my implementation - 1,2,3,null,null,4,5,null,null,null,null,
- deserialization implementation is a bit tricky as well - i knew that my level order traversal is like a perfect binary tree - so, i increment i twice inside the loop without worrying about it exceeding value's length

```java
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder serializedString = new StringBuilder();
        if (root == null) return serializedString.toString();

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        serializedString.append(root.val + ",");

        while (!deque.isEmpty()) {

            TreeNode node = deque.removeFirst();

            if (node.left == null) {
                serializedString.append("null,");
            } else {
                deque.addLast(node.left);
                serializedString.append(node.left.val + ",");
            }

            if (node.right == null) {
                serializedString.append("null,");
            } else {
                deque.addLast(node.right);
                serializedString.append(node.right.val + ",");
            }
        }

        return serializedString.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data.equals("")) return null;

        String[] values = data.split(",");

        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = stringToTreeNode(values[0]);
        deque.addLast(root);

        for (int i = 1; i < values.length;) {

            TreeNode parent = deque.removeFirst();

            TreeNode nodeLeft = stringToTreeNode(values[i]);
            parent.left = nodeLeft;
            if (nodeLeft != null) deque.addLast(nodeLeft);
            i += 1;

            TreeNode nodeRight = stringToTreeNode(values[i]);
            parent.right = nodeRight;
            if (nodeRight != null) deque.addLast(nodeRight);
            i += 1;
        }

        return root;
    }

    private TreeNode stringToTreeNode(String nodeValue) {
        if (nodeValue.equals("null")) return null;
        return new TreeNode(Integer.parseInt(nodeValue));
    }
}

```
