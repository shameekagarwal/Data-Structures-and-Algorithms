# All Nodes Distance K in Binary Tree

- https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
- can be at top or bottom
- we cannot travel towards parent
- for target node - 
  - they can give the node directly (i.e. the address) - like in this question
  - otherwise, they can give the value - require searching?
- idea - make the parent pointers - this can be done using a level order traversal
- we can use a hash map for storing the parent pointers
- then do a basic bfs traversal
- remember to use "visited"
- look at how bfs is just like level order traversal again
- but, it makes use of that extra nested loop so that we get all nodes at a distance k from a node
- this way, when the current distance becomes k, we can simply break out
- all elements in the queue contain all elements at distance k
- intuition - why we chose bfs and not dfs - we know "distance k" - this is done using bfs easily

```java
class Solution {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        
        List<Integer> result = new ArrayList<>();
        if (root == null || target == null) return result;
        
        Map<TreeNode, TreeNode> parentPointers = populateParentPointers(root);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(target);
        int currentLevel = 0;
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);

        while (!queue.isEmpty() && currentLevel < k) {

            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {
                
                TreeNode node = queue.removeFirst();

                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.addLast(node.left);
                }

                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.addLast(node.right);
                }

                if (parentPointers.containsKey(node) && !visited.contains(parentPointers.get(node))) {
                    visited.add(parentPointers.get(node));
                    queue.addLast(parentPointers.get(node));
                }
            }

            // for (TreeNode node : queue) {
            //     System.out.printf("%d, ", node.val);
            // }
            // System.out.println();

            currentLevel += 1;
        }

        while (!queue.isEmpty()) {
            result.add(queue.removeFirst().val);
        }
        
        return result;
    }

    private Map<TreeNode, TreeNode> populateParentPointers(TreeNode root) {
        
        Map<TreeNode, TreeNode> parentPointers = new HashMap<>();
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            
            TreeNode parent = queue.removeFirst();
            
            if (parent.left != null) {
                parentPointers.put(parent.left, parent);
                queue.addLast(parent.left);
            }

            if (parent.right != null) {
                parentPointers.put(parent.right, parent);
                queue.addLast(parent.right);
            }
        }

        return parentPointers;
    }
}
```
