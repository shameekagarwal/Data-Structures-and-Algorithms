# Binary Tree Zigzag Level Order Traversal

- https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
- my initial approach - use a deque instead of queue, use addFirst / addLast depending on direction - it will not work, because for e.g. it influences the direction the nodes in the next level are added as well - we remove the last element from the current level, its left nd right get added to queue
- so, just remember to not use deque for interview
- keep inverting boolean
- initialize array - and fill it either from back or from front
- why use `Integer[]` and not `int[]` - `Line 28: error: incompatible types: inference variable T has incompatible bounds`

```java
class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean leftToRight = true;

        if (root == null) return result;
        deque.addLast(root);

        while (!deque.isEmpty()) {

            int currentLevelSize = deque.size();
            Integer[] currentLevel = new Integer[currentLevelSize];

            for (int i = 0; i < currentLevelSize; i++) {

                TreeNode current = deque.removeFirst();
                int idx = leftToRight ? i : currentLevelSize - 1 - i;
                currentLevel[idx] = current.val;

                if (current.left != null) deque.addLast(current.left);
                if (current.right != null) deque.addLast(current.right);
            }

            leftToRight = !leftToRight;
            result.add(Arrays.asList(currentLevel));
        }

        return result;
    }
}
```
