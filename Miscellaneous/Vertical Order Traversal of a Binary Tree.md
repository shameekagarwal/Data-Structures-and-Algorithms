# Vertical Order Traversal of a Binary Tree

- https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
- lookup of col, row
- now, col should be all from minVertical to maxVertical - col me ya to -1 ya to +1 hota hai
- but for a column - rows need not be consecutive - vertical with value 1 can have rows 2 and 5
- how do we iterate over rows in order? - agar `map<int, map<int, list<value>>>` le le, to how to ensure all rows are processed in order
- option 1 - tree map - should automatically sort the keys
- option 2 - linked hash map - we anyway process rows in order - we are doing level order traversal
- why did i need a lookup by row and column - because same row, column ko sort karna hai by values
- if left to right, top to bottom was fine - i think we could have simply used `map<int, list<int>>`

```java
class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        if (root == null) return List.of();

        Map<Integer, Map<Integer, List<Integer>>> lookup = new HashMap<>();

        Deque<QueueNode> queue = new ArrayDeque<>();
        queue.addLast(new QueueNode(root, 0, 0));

        int minVertical = 0;
        int maxVertical = 0;

        while (!queue.isEmpty()) {

            QueueNode queueNode = queue.removeFirst();

            minVertical = Math.min(minVertical, queueNode.col);
            maxVertical = Math.max(maxVertical, queueNode.col);

            if (!lookup.containsKey(queueNode.col)) {
                lookup.put(queueNode.col, new LinkedHashMap<>());
            }

            if (!lookup.get(queueNode.col).containsKey(queueNode.row)) {
                lookup.get(queueNode.col).put(queueNode.row, new ArrayList<>());
            }

            lookup.get(queueNode.col).get(queueNode.row).add(queueNode.node.val);

            if (queueNode.node.left != null) {
                queue.addLast(new QueueNode(queueNode.node.left, queueNode.row + 1, queueNode.col - 1));
            }

            if (queueNode.node.right != null) {
                queue.addLast(new QueueNode(queueNode.node.right, queueNode.row + 1, queueNode.col + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = minVertical; i <= maxVertical; i++) {

            List<Integer> vertical = new ArrayList<>();
            result.add(vertical);

            for (List<Integer> row : lookup.get(i).values()) {
                Collections.sort(row);
                vertical.addAll(row);
            }
        }

        return result;
    }

    static class QueueNode {

        TreeNode node;
        int row;
        int col;

        QueueNode(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}
```
