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
- remember the use of dummy nodes to get rid of null checks

```java
class LRUCache {

    private int capacity;

    private Map<Integer, Node> map;

    private Node head;

    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeDLL(node);
        insertDLL(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            removeDLL(map.get(key));
            map.remove(key);
        }
        if (map.size() == capacity) {
            map.remove(head.next.key);
            removeDLL(head.next);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        insertDLL(node);
    }

    private void removeDLL(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertDLL(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    static class Node {
        
        Node prev;
        Node next;
        int val;
        int key;
        
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return "[" + val + "]";
        }
    }
}
```
