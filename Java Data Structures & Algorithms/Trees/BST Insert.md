# Points

- duplicates not allowed
- note how we use an infinite loop
- if value is less
  - if left is empty - insert at left
  - else navigate to left
- if value is greater
  - if right is empty - insert at right
  - else navigate to right

# Solution

```java
public class BinarySearchTree {
	
    private Node root;

    class Node {
        public int value;
        public Node left;
        public Node right;

        Node(int value) {
            this.value = value;
        }
    }

	public Node getRoot() {
        return root;
    }
    
    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (temp.value < newNode.value) {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            } else if (temp.value > newNode.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                return false;
            }
        }
    }
}
```
