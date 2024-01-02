# Contains

https://practice.geeksforgeeks.org/problems/search-a-node-in-bst/1

```java
class BST {
    boolean search(Node root, int x) {
        if (root == null) return false;
        if (root.data < x) return search(root.right, x);
        if (root.data > x) return search(root.left, x);
        return true;
    }
}
```

# Insert

https://practice.geeksforgeeks.org/problems/insert-a-node-in-a-bst/1

```java
class Solution {
    Node insert(Node root, int Key) {
        if (root == null) return new Node(Key);
        if (root.data < Key) {
            root.right = insert(root.right, Key);
        } else if (root.data > Key) {
            root.left = insert(root.left, Key);
        }
        return root;
    }
}
```

# Delete

https://practice.geeksforgeeks.org/problems/delete-a-node-from-bst/1

```java
class Tree {
    public static Node deleteNode(Node node, int X) {
        
        if (node == null) return null;
        
        if (node.data < X) {
            node.right = deleteNode(node.right, X);
        } else if (node.data > X) {
            node.left = deleteNode(node.left, X);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                int minValueInRightSubtree = minValue(node.right);
                node.data = minValueInRightSubtree;
                node.right = deleteNode(node.right, minValueInRightSubtree);
            }
        }
        
        return node;
    }
    
    private static int minValue(Node node) {
        if (node == null) return Integer.MAX_VALUE;
        if (node.left == null) return node.data;
        return minValue(node.left);
    }
}
```
