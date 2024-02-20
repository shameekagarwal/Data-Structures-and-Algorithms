# Bottom View of Binary Tree

- https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
- reuse [Vertical Order Traversal of a Binary Tree](./Vertical%20Order%20Traversal%20of%20a%20Binary%20Tree.md)
- we need last one of each vertical line - which automatically happens when using level order traversal
- boundary case note - "If there are multiple bottom-most nodes for a horizontal distance from root, then print the later one in level traversal.". look at the second example - 3 and 4 compete, but we chose 4. again this is ensured in level order traversal
- so, overwriting helps with choosing both bottommost and rightmost

```java
class Solution {

    public ArrayList<Integer> bottomView(Node root) {
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        Deque<NodeVerticalPair> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new TreeMap<>();
        
        if (root == null) return result;

        queue.addLast(new NodeVerticalPair(root, 0));
        
        while (!queue.isEmpty()) {
            
            NodeVerticalPair current = queue.removeFirst();
            map.put(current.vertical, current.node.data);
            
            if (current.node.left != null) {
                queue.addLast(new NodeVerticalPair(current.node.left, current.vertical - 1));
            }
            
            if (current.node.right != null) {
                queue.addLast(new NodeVerticalPair(current.node.right, current.vertical + 1));
            }
        }
        
        for (int val : map.values()) {
            result.add(val);
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
