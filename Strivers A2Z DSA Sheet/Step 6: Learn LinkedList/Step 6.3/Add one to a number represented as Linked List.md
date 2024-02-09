# Add one to a number represented as Linked List

- https://www.codingninjas.com/studio/problems/add-one-to-a-number-represented-as-linked-list_920557

## Reverse

- striver solves it by reversing the list
- time complexity - O(3 * n)

```java
import java.util.* ;
import java.io.*; 

public class Solution {
	
	public static Node addOne(Node head) {
		
		Node tail = reverse(head);
		addOneBackwards(tail);
		return reverse(tail);
	}

	private static void addOneBackwards(Node head) {

		int carry = 1;
		Node curr = head;
		Node prev = null;
		
		while (carry == 1 && curr != null) {
			int newData = curr.data + 1;
			curr.data = newData % 10;
			carry = newData / 10;
			prev = curr;
			curr = curr.next;
		}

		if (carry == 1) {
			prev.next = new Node(1);
		}
	}

	private static Node reverse(Node head) {

		Node prev = null;
		Node curr = head;

		while (curr != null) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}
}
```

## Recursive

- we need to go from back to front
- so, solve using recursion, like an "inbuilt" stack
- time complexity - O(n)

```java
import java.util.* ;
import java.io.*;

public class Solution {
	
	public static Node addOne(Node head) {
		int carry = addNodeRec(head);
		if (carry == 1) {
			Node newHead = new Node(1);
			newHead.next = head;
			return newHead;
		}
		return head;
	}

	private static int addNodeRec(Node head) {
		if (head == null) return 1;
		int carry = addNodeRec(head.next);
		head.data += carry;
		if (head.data == 10) {
			head.data = 0;
			return 1;
		}
		return 0;
	}
}
```
