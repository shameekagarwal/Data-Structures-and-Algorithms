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

    private Map<Integer, DLL.Node> lookup;
    private int capacity;
    private DLL dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lookup = new HashMap<>();
        dll = new DLL();
    }
    
    public int get(int key) {

        if (!lookup.containsKey(key)) {
            return -1;
        }
        
        DLL.Node node = lookup.get(key);
        dll.delete(node);
        dll.insert(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        
        if (lookup.containsKey(key)) {
            DLL.Node toDelete = lookup.get(key);
            dll.delete(toDelete);
            lookup.remove(key);
        }

        if (lookup.size() == capacity) {
            lookup.remove(dll.head.next.key);
            dll.delete(dll.head.next);
        }

        DLL.Node node = new DLL.Node(key, value);
        dll.insert(node);
        lookup.put(key, node);
    }
}

class DLL {

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

    void delete(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node current = head.next;

        while (current != tail) {
            sb.append(current + "->");
            current = current.next;
        }

        return sb.toString();
    }

    static class Node {
        
        Node prev;
        Node next;
        int key;
        int value;

        Node(int key, int value) {
            prev = null;
            next = null;
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }
}

```

## Java Solution

- the entire thing can be done using java stl?

```java
class LRUCache {

    private Map<Integer, Integer> lookup;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lookup = new LinkedHashMap<>();
    }
    
    public int get(int key) {

        if (!lookup.containsKey(key)) {
            return -1;
        }

        int value = lookup.get(key);
        lookup.remove(key);
        lookup.put(key, value);
        return value;
    }
    
    public void put(int key, int value) {

        if (lookup.containsKey(key)) {
            lookup.remove(key);
        }

        if (lookup.size() == capacity) {
            lookup.remove(lookup.keySet().iterator().next());
        }

        lookup.put(key, value);
    }
}
