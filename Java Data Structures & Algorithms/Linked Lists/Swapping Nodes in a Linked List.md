# Points

- https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
- for some reason, i felt the problem should not allow touching val
- remember the two pointer technique used here - to find kth node, node before kth node, kth from end node, node before kth from end node
- checking boundary conditions separately makes it very easy - 
  - kth node and kth node from end sre same
  - kth node is head
  - kth node from at end is head
  - kth node after kth from end node
  - kth from end node after kth node
  - general - they are not next to each other, not at ends and not the same

# Solution

```java
class Solution {
    public ListNode swapNodes(ListNode head, int k) {

        if (head == null || head.next == null) return head;
        
        k -= 1;
        
        ListNode nodeBeforeKthNode, kthNode, nodeBeforeKthFromEndNode, kthFromEndNode;

        nodeBeforeKthNode = null;
        ListNode it = head;
        for (int i = 0; i < k; i++) {
            nodeBeforeKthNode = it;
            it = it.next;
        }
        kthNode = it;

        nodeBeforeKthFromEndNode = null;
        kthFromEndNode = head;
        while (it.next != null) {
            it = it.next;
            nodeBeforeKthFromEndNode = kthFromEndNode;
            kthFromEndNode = kthFromEndNode.next;
        }

        if (kthFromEndNode == kthNode) {
            return head;
        } else if (nodeBeforeKthNode == null) {
            nodeBeforeKthFromEndNode.next = kthNode;
            kthFromEndNode.next = kthNode.next;
            kthNode.next = null;
            return kthFromEndNode;
        } else if (nodeBeforeKthFromEndNode == null) {
            nodeBeforeKthNode.next = kthFromEndNode;
            kthNode.next = kthFromEndNode.next;
            kthFromEndNode.next = null;
            return kthNode;
        } else if (kthFromEndNode.next == kthNode) {
            nodeBeforeKthFromEndNode.next = kthNode;
            kthFromEndNode.next = kthNode.next;
            kthNode.next = kthFromEndNode;
            return head;
        } else if (kthNode.next == kthFromEndNode) {
            nodeBeforeKthNode.next = kthFromEndNode;
            kthNode.next = kthFromEndNode.next;
            kthFromEndNode.next = kthNode;
            return head;
        } else {
            nodeBeforeKthNode.next = kthFromEndNode;
            nodeBeforeKthFromEndNode.next = kthNode;
            ListNode nodeAfterKthFromEndNode = kthFromEndNode.next;
            kthFromEndNode.next = kthNode.next;
            kthNode.next = nodeAfterKthFromEndNode;
            return head;
        }
    }
}
```
