# Vertical Order Traversal of a Binary Tree

- https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
- use level order traversal
- use we store x, y in the level order traversal elements
- we can have elements with the same x and y - refer the example `[1,2,3,4,5,6,7]` - look at 5 and 6
- decrement x for vertical, increment it for horizontal
- nodes at same x and y need to be sorted by their values - this is why i think we need to store y as well - to identify nodes which are at the same x and y
- otherwise, we could have gotten away without storing y as well i think - we would just have a `map<int, List<integer>>`, where the key would be just x
- using tree map for automatic sorted nature - but remember about the additional log time complexity that comes with it
- sort values at same x and y before adding them to the result - maybe we could have used a priority queue instead of List as well here. note - i think 

```java
class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Map<Integer, List<Integer>>> map = new TreeMap<>();
        Deque<TraversalCoordinates> queue = new ArrayDeque<>();
        queue.add(new TraversalCoordinates(root, 0, 0));

        while (!queue.isEmpty()) {

            TraversalCoordinates current = queue.removeFirst();

            if (!map.containsKey(current.x)) map.put(current.x, new TreeMap<>());
            if (!map.get(current.x).containsKey(current.y)) map.get(current.x).put(current.y, new ArrayList<>());
            map.get(current.x).get(current.y).add(current.node.val);

            if (current.node.left != null) {
                queue.addLast(new TraversalCoordinates(current.node.left, current.x - 1, current.y + 1));
            }

            if (current.node.right != null) {
                queue.addLast(new TraversalCoordinates(current.node.right, current.x + 1, current.y + 1));
            }
        }

        for (Map.Entry<Integer, Map<Integer, List<Integer>>> vertical : map.entrySet()) {
            result.add(new ArrayList<>());
            for (Map.Entry<Integer, List<Integer>> entry : vertical.getValue().entrySet()) {
                Collections.sort(entry.getValue());
                result.get(result.size() - 1).addAll(entry.getValue());
            }
        }

        return result;
    }

    static class TraversalCoordinates {
        
        TreeNode node;
        int x, y;

        TraversalCoordinates(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;    
        }
    }
}
```
