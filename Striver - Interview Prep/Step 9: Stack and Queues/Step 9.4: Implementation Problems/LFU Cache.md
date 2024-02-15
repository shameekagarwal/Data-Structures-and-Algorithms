# LFU Cache

- https://leetcode.com/problems/lfu-cache/
- implement each key as an lru cache
- it is inspired by [LRU Cache](./LRU%20Cache.md)
- note - i was thinking of using a priority queue instead of a doubly linked list - and the value would be based on - 
  - frequency of access
  - the "number of total operations on cache till now" for the lru like functionality
- so a frequency lookup is used - (frequency, dll of nodes)
- in this frequency lookup, each value is an lru cache in itself
- also, a node lookup is used
- just read through code, logic is not complex, implementation is

```java
class LFUCache {

    int capacity, minFrequency;
    Map<Integer, DLL> frequencyLookup;
    Map<Integer, DLL.Node> nodeLookup;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minFrequency = 0;
        frequencyLookup = new HashMap<>();
        nodeLookup = new HashMap<>();
    }
    
    public int get(int key) {
        
        if (!nodeLookup.containsKey(key)) return -1;
        
        DLL.Node nodeToReturn = nodeLookup.get(key);
        incrementNodeFrequency(nodeToReturn);

        // System.out.printf("getting key: %d\n", key);
        // System.out.println(nodeLookup);
        // System.out.println(frequencyLookup);
        // System.out.println("---------------");

        return nodeToReturn.value;
    }
    
    public void put(int key, int value) {
        
        // node with the same key exists - update frequency, value and frequency lookup
        if (nodeLookup.containsKey(key)) {
            
            DLL.Node nodeToUpdate = nodeLookup.get(key);
            nodeToUpdate.value = value;
            incrementNodeFrequency(nodeToUpdate);
            return;
        }
        
        // capacity is reached
        if (capacity == nodeLookup.size()) {

            DLL.Node nodeToRemove = frequencyLookup.get(minFrequency).head.next;
            frequencyLookup.get(minFrequency).remove(nodeToRemove);
            nodeLookup.remove(nodeToRemove.key);
        }
        
        // reset min frequency
        minFrequency = 1;

        // add node
        DLL.Node nodeToInsert = new DLL.Node(key, value);
        safeInsertIntoFrequencyLookup(nodeToInsert);
        nodeLookup.put(key, nodeToInsert);

        // System.out.printf("putting key: %d, value: %d\n", key, value);
        // System.out.println(nodeLookup);
        // System.out.println(frequencyLookup);
        // System.out.println("---------------");
    }

    private void incrementNodeFrequency(DLL.Node node) {

        // remove from frequency lookup
        frequencyLookup.get(node.frequency).remove(node);
        
        // update frequencies
        if (minFrequency == node.frequency && frequencyLookup.get(node.frequency).isEmpty()) {
            minFrequency += 1;
        }
        node.frequency += 1;

        safeInsertIntoFrequencyLookup(node);
    }

    private void safeInsertIntoFrequencyLookup(DLL.Node node) {
        if (!frequencyLookup.containsKey(node.frequency)) {
            frequencyLookup.put(node.frequency, new DLL());
        }
        frequencyLookup.get(node.frequency).insert(node);
    }
}

class DLL {

    Node tail, head;
    int size;

    DLL() {
        size = 0;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node it = head;
        while (it != null) {
            sb.append(it.toString());
            sb.append("=");
            it = it.next;
        }
        sb.append("\n");
        return sb.toString();
    }

    static class Node {

        int key, value, frequency;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            frequency = 1;
            prev = null;
            next = null;
        }

        @Override
        public String toString() {
            return "(key: " + key + ", value: " + value + ", frequency: " + frequency + ")";
        }
    }
}
```
