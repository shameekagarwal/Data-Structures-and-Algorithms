# Inserting a node in LinkedList

- https://www.codingninjas.com/studio/problems/insert-node-at-the-beginning_8144739

```java
public class Solution {
    
    public static Node insertAtFirst(Node list, int newValue) {
        Node newNode = new Node(newValue);
        if (list != null) {
            newNode.next = list;
            list.prev = newNode;
        }
        return newNode;
    }
}

/****************************************************************

 Following is the class structure of the Node class:

 class Node {
     public int data;
     public Node next;
     public Node prev;

     Node()
     {
         this.data = 0;
         this.next = null;
         this.prev = null;
     }

     Node(int data)
     {
         this.data = data;
         this.next = null;
         this.prev = null;
     }

     Node(int data, Node next)
     {
         this.data = data;
         this.next = next;
         this.prev = next;
     }
 };

 *****************************************************************/

```
