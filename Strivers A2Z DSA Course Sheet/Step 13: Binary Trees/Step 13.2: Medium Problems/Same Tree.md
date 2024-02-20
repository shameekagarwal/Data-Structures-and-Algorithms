# Same Tree

- https://leetcode.com/problems/same-tree/
- can do [any of the four traversals](../Step%2013.1:%20Traversals)

```java
class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null || q == null) {
            return ((p == null) && (q == null));
        }

        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```
