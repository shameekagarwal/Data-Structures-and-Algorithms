# DFS - In Order

- https://www.geeksforgeeks.org/problems/inorder-traversal/1

```java
class Solution {
    ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        inOrder(root, result);
        return result;
    }
    
    private void inOrder(Node node, ArrayList<Integer> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.data);
        inOrder(node.right, result);
    }
}
```

# DFS - Pre Order

- https://www.geeksforgeeks.org/problems/preorder-traversal/1

```java
class BinaryTree
{
    static ArrayList<Integer> preorder(Node root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }
    
    private static void preorder(Node node, ArrayList<Integer> result) {
        if (node == null) return;
        result.add(node.data);
        preorder(node.left, result);
        preorder(node.right, result);
    }

}
```

# DFS - Post Order

- https://www.geeksforgeeks.org/problems/postorder-traversal/1

```java
class Tree
{
    ArrayList<Integer> postOrder(Node root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        postOrder(root, result);
        return result;
    }
    
    private void postOrder(Node node, ArrayList<Integer> result) {
        if (node == null) return;
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.data);
    }
}
```

# BFS - Level Order

- https://www.geeksforgeeks.org/problems/level-order-traversal/1

```java
class Solution
{
    static ArrayList <Integer> levelOrder(Node node)
    {
        java.util.Deque<Node> dequeue = new java.util.ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        dequeue.add(node);
        while (!dequeue.isEmpty()) {
            Node currentNode = dequeue.removeLast();
            if (currentNode.left != null) dequeue.addFirst(currentNode.left);
            if (currentNode.right != null) dequeue.addFirst(currentNode.right);
            result.add(currentNode.data);
        }
        return result;
    }
}
```
