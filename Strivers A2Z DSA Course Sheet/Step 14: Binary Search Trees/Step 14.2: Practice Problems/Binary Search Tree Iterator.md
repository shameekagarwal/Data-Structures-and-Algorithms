# Binary Search Tree Iterator

- https://leetcode.com/problems/binary-search-tree-iterator/
- one solution - construct entire in order in the constructor itself, then both next and has next become O(1) since we maintain a pointer
- but i do not think that is what the question is about
- we use [this](../../Step%2013:%20Binary%20Trees/Step%2013.1:%20Traversals/Iterative%20Inorder%20Traversal%20of%20Binary%20Tree.md) as is to help traverse using stack
- note - but we do not perform the entire thing
- initialize left with the root
- for next - 
  - push left of current while left is not null
  - top of stack is the value to return
  - now, update current to top's right
- we basically get rid of the outer loop in the iterative traversal - we only traverse "till needed" to get the next element in the in order
- for has next, we do not need to even do this - as long as 
  - either current node is not null
  - or stack is not empty
- we have a next node to go to
- so, next has time complexity O(H)
- hasNext has time complexity O(1)
- solution has space complexity O(H)
- note - so, i do not think my iterator in any way makes use of the fact that the binary tree is bst - same solution would work for binary tree as well

```java
class BSTIterator {

    private Deque<TreeNode> stack;
    private TreeNode currentNode;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        currentNode = root;
    }
    
    public int next() {
        while (currentNode != null) {
            stack.addLast(currentNode);
            currentNode = currentNode.left;
        }
        if (stack.isEmpty()) return -1;
        int value = stack.peekLast().val;
        currentNode = stack.removeLast().right;
        return value;
    }

    public boolean hasNext() {
        return !(currentNode == null && stack.isEmpty());
    }
}
```
