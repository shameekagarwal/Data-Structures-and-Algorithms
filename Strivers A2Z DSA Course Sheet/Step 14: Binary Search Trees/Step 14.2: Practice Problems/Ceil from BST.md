# Ceil from BST

- https://www.codingninjas.com/studio/problems/ceil-from-bst_920464
- if current value is greater than or equal to ceil, update ceil and move left
- else move right

```java
import java.util.*;
import java.io.*;

public class Solution {

    public static int findCeil(TreeNode<Integer> node, int x) {

        int ceil = -1;

        while (node != null) {
            if (node.data == x) {
                ceil = node.data;
                break;
            } else if (node.data > x) {
                ceil = node.data;
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return ceil;
    }
}
```
