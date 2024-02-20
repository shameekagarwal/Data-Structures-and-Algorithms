# Level Order Traversal

- put root in queue
- repeat steps below till queue does not become empty
- store the queue size - this is the number of nodes in the current level
- by doing this, we can return list of lists i.e. a separate list for each level
- remove from queue and to current level list
- add left to queue if not null
- add right to queue if not nul
- add current level list to answer
- time complexity - O(N)
- space complexity - O(N)
- https://leetcode.com/problems/binary-tree-level-order-traversal/
- remember the null check for root i.e. empty tree

```java
class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();

        if (root == null) return result;
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode currentNode = queue.removeFirst();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null) queue.addLast(currentNode.left);
                if (currentNode.right != null) queue.addLast(currentNode.right);
            }
            result.add(currentLevel);
        }

        return result;
    }
}
```
