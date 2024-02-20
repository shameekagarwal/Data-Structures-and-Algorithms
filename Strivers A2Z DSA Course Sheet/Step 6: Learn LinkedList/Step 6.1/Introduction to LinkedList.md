# Introduction to LinkedList

- arrays - elements stored in contiguous memory locations. implications - 
  - traversing an array via index is easy
  - adding an element is not easy
- linked list - not in contiguous locations
- this is achieved via next pointer for every element
- 64 bit machine means next would need 8 bytes
- https://www.codingninjas.com/studio/problems/introduction-to-linked-list_8144737

```java
public class Solution {
    
    public static Node constructLL(int []arr) {
        
        Node head = new Node(arr[0]);
        Node prev = head;
        
        for (int i = 1; i < arr.length; i++) {
            Node current = new Node(arr[i]);
            prev.next = current;
            prev = current;
        }
        
        return head;
    }
}

/****************************************************************

 Following is the class structure of the Node class:

 class Node {
     public int data;
     public Node next;

     Node()
     {
         this.data = 0;
         this.next = null;
     }

     Node(int data)
     {
         this.data = data;
         this.next = null;
     }

     Node(int data, Node next)
     {
         this.data = data;
         this.next = next;
     }
 };

 *****************************************************************/
```
