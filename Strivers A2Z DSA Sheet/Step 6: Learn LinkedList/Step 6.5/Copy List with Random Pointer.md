# Copy List with Random Pointer

- https://leetcode.com/problems/copy-list-with-random-pointer/

## Brute

- using map, store actual as key and dummy as value
- this way, we can populate pointers
- issue - extra `O(N)` space complexity for map

```java
import java.util.Map;
import java.util.HashMap;

class Solution {

    public Node copyRandomList(Node head) {
        
        Map<Node, Node> copyLookup = getCopyLookup(head);
        populatePointers(copyLookup);
        return copyLookup.get(head);
    }

    private void populatePointers(Map<Node, Node> copyLookup) {
        for (Map.Entry<Node, Node> entry : copyLookup.entrySet()) {
            if (entry.getKey().next != null) {
                entry.getValue().next = copyLookup.get(entry.getKey().next);
            }
            if (entry.getKey().random != null) {
                entry.getValue().random = copyLookup.get(entry.getKey().random);
            }
        }
    }

    private Map<Node, Node> getCopyLookup(Node head) {
        
        Map<Node, Node> copyLookup = new HashMap<>();
        Node current = head;
        
        while (current != null) {
            copyLookup.put(current, getCopy(current));
            current = current.next;
        }

        return copyLookup;
    }

    private Node getCopy(Node actual) {
        return new Node(actual.val);
    }
}
```

## Optimal

- step 1 - insert copies in between - e.g. 1 -> 2 -> 3 would become 1 -> 1(c) -> 2 -> 2(c) -> 3 -> 3(c)
- step 2 - populate random pointer - e.g. `current.next.random = current.random.next` - remember to put null check here if random points to null
- step 3 - revert changes and extract copy
- new head is original head's next
- current's next is current's next's next
- copy node's next is copy node's next's next - remember to place null check if this is the last copy node of the list 

```java
class Solution {

    public Node copyRandomList(Node head) {

        if (head == null) return null;

        insertCopiesInBetween(head);
        populateRandom(head);
        return revertChangesAndExtractCopy(head);
    }

    private Node revertChangesAndExtractCopy(Node head) {

        Node current = head;
        Node newHead = head.next;

        while (current != null) {
            Node copyNode = current.next;
            Node nextCurrent = copyNode.next;
            if (nextCurrent != null) {
                copyNode.next = nextCurrent.next;
            }
            current.next = nextCurrent;
            current = current.next;
        }

        return newHead;
    }

    private void populateRandom(Node head) {
        
        Node current = head;
        
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
    }

    private void insertCopiesInBetween(Node head) {

        Node current = head;
        
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = current.next.next;
        }
    }
}
```
