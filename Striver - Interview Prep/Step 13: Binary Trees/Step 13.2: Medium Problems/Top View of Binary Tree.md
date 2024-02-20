# Top View of Binary Tree

- https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
- reuse [Vertical Order Traversal of a Binary Tree](./Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.md)
- imagine a vertical line passes through each set of nodes
- we need first one of each vertical line - which automatically happens when using level order traversal
- again using TreeMap to make life easier - possible with HashMap but involves more code, e.g. handling negative values for x etc

```java
class Solution {

    static ArrayList<Integer> topView(Node root) {

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Integer> map = new TreeMap<>();
        Deque<NodeVerticalPair> queue = new ArrayDeque<>();
        queue.addLast(new NodeVerticalPair(root, 0));

        while (!queue.isEmpty()) {

            NodeVerticalPair current = queue.removeFirst();
            if (!map.containsKey(current.vertical)) map.put(current.vertical, current.node.data);
            if (current.node.left != null) queue.addLast(new NodeVerticalPair(current.node.left, current.vertical - 1));
            if (current.node.right != null) queue.addLast(new NodeVerticalPair(current.node.right, current.vertical + 1));
        }
        
        for (int value : map.values()) {
            result.add(value);
        }
        
        return result;
    }
    
    static class NodeVerticalPair {
        
        Node node;
        int vertical;
        
        NodeVerticalPair(Node node, int vertical) {
            this.node = node;
            this.vertical = vertical;
        }
    }
}
```
