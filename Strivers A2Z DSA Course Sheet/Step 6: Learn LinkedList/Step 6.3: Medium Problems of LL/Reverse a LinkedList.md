# Reverse a LinkedList

## Brute

- put in stack in first iteration
- pop out of stack and change pointers in next iteration

```java
class Solution {

    public ListNode reverseList(ListNode head) {

        if (head == null) return null;

        List<ListNode> list = new ArrayList<>();
        ListNode it = head;
        while (it != null) {
            list.add(it);
            it = it.next;
        }

        for (int i = list.size() - 1; i > 0; i--) {
            list.get(i).next = list.get(i - 1);
        }
        list.get(0).next = null;

        return list.get(list.size() - 1);
    }
}
```

## Iterative

```java
class Solution {
    
    public ListNode reverseList(ListNode head) {

        ListNode current = head;
        ListNode prev = null;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
```

## Recursive

- imagine we are at position x
- we want everything from end up to x + 1 to be reversed
- vvimp - this includes node at x + 1 pointing to null i.e. see how we do `head.next = null`. otherwise, we get the final answer like this - 
  ```
  input - 1 -> 2 -> 3 -> 4 -> 5
  output - 1 -> 2 and 5 -> 4 -> 3 -> 2 -> 1. so, cycle at 1 and 2
  ```

```java
class Solution {
    
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
```
