# Delete Node in a Linked List

- https://leetcode.com/problems/delete-node-in-a-linked-list/
- in place modification is done

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;
        ListNode currentNode = node;
        ListNode prevNode = null;
        while (nextNode != null) {
            currentNode.val = nextNode.val;
            prevNode = currentNode;
            currentNode = nextNode;
            nextNode = nextNode.next;
        }
        prevNode.next = null;
    }
}
```
