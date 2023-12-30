- complete tree
- max heap - maximum value is at the top, every node is larger than its descendants
- min heap - minimum value ....
- unlike tree, heaps are usually implemented via arrays
- some implementations might leave index 0 as empty, your wish
- if we leave index 0 empty, access children at (2n, 2n + 1)
  - if not, access children at (2n + 1, 2n + 2)
- if we leave index 0 empty, access parent at (n // 2) (integer division)
  - if not, access parent at (n - 1 // 2)
- whenever performing an operation, remember to keep the heap as a complete tree
- when inserting - 
  - insert at end - since it is a complete tree, we fill left to right, or end of array
  - keep comparing with parent. if it is larger than parent, swap, else stop
- refer the code modularity for interview - creating helper methods like left child, right child and swap
- when removing - 
  - we only remove the top most element from the heap i.e. the element at index 0
  - remove the element at index 0
  - then, make the tree complete - place the bottom most + right most element, or end of array at the top
  - bubble this element down - 
    - compare it with both left and right child
    - swap it with the biggest child
    - continue the loop
- heaps are always automatically balanced since they are always complete, which explains the complexities below
- removing - o(log n)
- adding - o(log n)
- remember for interview - default in java is min pq
  ```java
  PriorityQueue<Integer> pq = new PriorityQueue<>();
  ```
- if we need max pq, use following - 
  ```java
  PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
  ```
