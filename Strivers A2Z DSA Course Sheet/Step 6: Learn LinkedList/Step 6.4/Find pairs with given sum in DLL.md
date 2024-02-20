# Find pairs with given sum in DLL

- https://www.codingninjas.com/studio/problems/find-pairs-with-given-sum-in-doubly-linked-list_1164172
- sorted - so two pointer

```java
import java.util.* ;
import java.io.*;

public class Solution {

    public static List<int[]> findPairs(Node head, int k) {
        
        Node tail = getTail(head);
        List<int[]> ans = new ArrayList<>();

        while (head != null && tail != null && head.data < tail.data) {
            if (head.data + tail.data < k) {
                head = head.next;
            } else if (head.data + tail.data > k) {
                tail = tail.prev;
            } else {
                ans.add(new int[]{head.data, tail.data});
                tail = tail.prev;
                head = head.next;
            }
        }
        return ans;
    }

    private static Node getTail(Node current) {
        
        if (current == null) return null;
        
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }
}

```
