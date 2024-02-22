# Minimum Element in BST

- https://www.codingninjas.com/studio/problems/minimum-element-in-bst_8160462
- leftmost element in a bst is the minimum of the bst

```java
public class Solution {

    public static int minValue(Node root) {
        if (root == null) return -1;
        else if (root.left == null) return root.data;
        else return minValue(root.left);
    }
}
```
