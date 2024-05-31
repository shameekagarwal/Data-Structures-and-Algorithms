# LRU Cache

- https://leetcode.com/problems/lru-cache/
- use linked list + map
- map - key, node address
- least recently used is at tail of linked list
- when adding to cache - 
  - if key already exists
    - get node from map to delete it from list
    - delete it from map
  - if size is already = capacity
    - get key of head from list to delete it from map
    - delete it from list
  - finally, add to tail of linked list and map
- when getting from cache - 
  - get the node from map
  - remove it from list and then add it back to list - this ensures we bring it back to the front, thus indicating it was recently used
- vvimp - remember the use of dummy nodes for both head and tail to get rid of null checks

```java
class LRUCache {

    private final Map<Integer, DLL.Node> lookup;
    private final DLL dll;
    private final int capacity;

    public LRUCache(int capacity) {
        lookup = new HashMap<>();
        dll = new DLL();
        this.capacity = capacity;
    }

    public int get(int key) {

        if (!lookup.containsKey(key)) {
            return -1;
        }

        bubbleUpKey(key);
        return lookup.get(key).value;
    }

    private void bubbleUpKey(int key) {
        dll.remove(lookup.get(key));
        dll.insert(lookup.get(key));
    }

    public void put(int key, int value) {

        if (lookup.containsKey(key)) {
            bubbleUpKey(key);
            lookup.get(key).value = value;
            return;
        }

        if (lookup.size() == capacity) {
            lookup.remove(dll.first().key);
            dll.remove(dll.first());
        }

        DLL.Node newNode = new DLL.Node(key, value);
        lookup.put(key, newNode);
        dll.insert(newNode);
    }

    static class DLL {

        Node head;
        Node tail;

        DLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        void insert(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }

        Node first() {
            return head.next == tail ? null : head.next;
        }

        static class Node {

            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                prev = null;
                next = null;
            }
        }
    }
}
```

## Java Solution

- the entire thing can be done using java stl?

```java
class LRUCache {

    private final Map<Integer, Integer> lookup;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lookup = new LinkedHashMap<>();
    }
    
    public int get(int key) {

        if (!lookup.containsKey(key)) {
            return -1;
        }

        bubbleKeyToEnd(key);
        return lookup.get(key);
    }

    private void bubbleKeyToEnd(int key) {
        int value = lookup.get(key);
        lookup.remove(key);
        lookup.put(key, value);
    }

    public void put(int key, int value) {

        if (lookup.containsKey(key)) {
            bubbleKeyToEnd(key);
            lookup.put(key, value);
            return;
        }

        if (lookup.size() == capacity) {
            lookup.remove(lookup.keySet().iterator().next());
        }

        lookup.put(key, value);
    }
}
```
