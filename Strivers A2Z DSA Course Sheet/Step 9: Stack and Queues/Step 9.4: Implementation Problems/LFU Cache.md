# LFU Cache

- https://leetcode.com/problems/lfu-cache/
- implement each key as an lru cache. it is inspired by [LRU Cache](./LRU%20Cache.md). "For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated."
- note - i was thinking of using a priority queue instead of a doubly linked list - and the value would be based on - 
  - frequency of access
  - the "number of total operations on cache till now" for the lru like functionality
- so a frequency lookup is used - (frequency, dll of nodes)
- in this frequency lookup, each value is an lru cache in itself
- also, a node lookup is used
- just read through code, logic is not complex, implementation is

```java
class LFUCache {

    private Map<Integer, DLL> frequencyLookup;
    private Map<Integer, DLL.Node> lookup;
    private int capacity;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        frequencyLookup = new HashMap<>();
        lookup = new HashMap<>();
        minFrequency = 0;
    }
    
    public int get(int key) {

        if (!lookup.containsKey(key)) {
            return -1;
        }

        accessKey(key);

        return lookup.get(key).value;
    }
    
    public void put(int key, int value) {

        if (lookup.containsKey(key)) {
            accessKey(key);
            lookup.get(key).value = value;
            return;
        }

        if (lookup.size() == capacity) {
            DLL.Node node = frequencyLookup.get(minFrequency).head.next;
            remove(node);
        }

        minFrequency = 1;
        DLL.Node node = new DLL.Node(key, value);
        insert(node);
    }

    private void accessKey(int key) {

        DLL.Node node = lookup.get(key);
        remove(node);

        if (minFrequency == node.frequency && frequencyLookup.get(node.frequency).isEmpty()) {
            minFrequency += 1;
        }

        node.frequency += 1;
        insert(node);
    }

    private void remove(DLL.Node node) {
        lookup.remove(node.key);
        frequencyLookup.get(node.frequency).remove(node);
    }

    private void insert(DLL.Node node) {
        lookup.put(node.key, node);
        if (!frequencyLookup.containsKey(node.frequency)) {
            frequencyLookup.put(node.frequency, new DLL());
        }
        frequencyLookup.get(node.frequency).insert(node);
    }
}

class DLL {

    Node head, tail;

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
    }

    boolean isEmpty() {
        return head.next == tail;
    }

    static class Node {
        
        int key, value;
        int frequency;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.frequency = 1;
            this.value = value;
        }
    }
}
```
