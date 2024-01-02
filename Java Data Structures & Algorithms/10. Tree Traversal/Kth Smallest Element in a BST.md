# Notes

- https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
- TODO: first read about iterative traversal of tree, then solve this using iterations

# Solution

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        State state = new State(k);
        kthSmallest(root, state);
        return state.kthSmallestNode.val;
    }

    private void kthSmallest(TreeNode node, State state) {

        if (node == null) return;

        kthSmallest(node.left, state);
        
        state.currentCount -= 1;
        if (state.currentCount == 0) state.kthSmallestNode = node;
        
        if (state.kthSmallestNode != null) return;
        
        kthSmallest(node.right, state);
    }
}

class State {
    
    TreeNode kthSmallestNode;
    Integer currentCount;
    
    State(Integer currentCount) {
        this.currentCount = currentCount;
    }
}

```
