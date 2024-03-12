# Add 2 numbers in LL

- https://leetcode.com/problems/add-two-numbers/

```java
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode leftDigit = l1;
        ListNode rightDigit = l2;
        ListNode resultHead = new ListNode(-1);
        ListNode resultDigit = resultHead;

        int carry = 0;

        while (leftDigit != null || rightDigit != null) {
            
            int sum = (leftDigit == null ? 0 : leftDigit.val) + (rightDigit == null ? 0 : rightDigit.val) + carry;
            resultDigit.next = new ListNode(sum % 10);
            carry = sum / 10;
            resultDigit = resultDigit.next;

            if (leftDigit != null) leftDigit = leftDigit.next;
            if (rightDigit != null) rightDigit = rightDigit.next;
        }

        if (carry != 0) {
            resultDigit.next = new ListNode(carry);
        }

        return resultHead.next;
    }
}
```
