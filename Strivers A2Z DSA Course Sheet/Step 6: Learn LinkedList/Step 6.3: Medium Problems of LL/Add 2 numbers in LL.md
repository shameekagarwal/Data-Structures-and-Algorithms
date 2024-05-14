# Add 2 numbers in LL

- https://leetcode.com/problems/add-two-numbers/

```java
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode current = head;

        while (l1 != null || l2 != null || carry != 0) {

            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = (v1 + v2 + carry) % 10;
            carry = (v1 + v2 + carry) / 10;

            ListNode newNode = new ListNode(sum);
            current.next = newNode;
            current = current.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return head.next;
    }
}
```
