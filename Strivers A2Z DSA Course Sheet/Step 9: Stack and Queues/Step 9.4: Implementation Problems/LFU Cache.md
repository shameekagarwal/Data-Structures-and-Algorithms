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

    private final int capacity;
    private int minFrequency;
    private final Map<Integer, DLL> cache;
    private final Map<Integer, DLL.Node> lookup;

    public LFUCache(int capacity) {

        this.capacity = capacity;
        minFrequency = 0;

        lookup = new HashMap<>();
        cache = new HashMap<>();
    }

    public int get(int key) {

        if (!lookup.containsKey(key)) {
            return -1;
        }

        bubbleUp(key);

        // System.out.println(cache);

        return lookup.get(key).value;
    }

    private void bubbleUp(int key) {

        int frequency = lookup.get(key).frequency;
        cache.get(frequency).remove(lookup.get(key));

        if (cache.get(frequency).isEmpty()) {
            cache.remove(frequency);

            if (minFrequency == frequency) {
                minFrequency += 1;
            }
        }

        frequency += 1;

        lookup.get(key).frequency = frequency;

        if (!cache.containsKey(frequency)) {
            cache.put(frequency, new DLL());
        }

        cache.get(frequency).insert(lookup.get(key));
    }
    
    public void put(int key, int value) {

        if (lookup.containsKey(key)) {
            bubbleUp(key);
            lookup.get(key).value = value;
            return;
        }

        if (lookup.size() == capacity) {
            DLL.Node nodeToRemove = cache.get(minFrequency).first();
            cache.get(minFrequency).remove(nodeToRemove);
            lookup.remove(nodeToRemove.key);
        }

        minFrequency = 1;
        DLL.Node newNode = new DLL.Node(key, value);
        lookup.put(key, newNode);

        if (!cache.containsKey(minFrequency)) {
            cache.put(minFrequency, new DLL());
        }

        cache.get(minFrequency).insert(newNode);

        // System.out.println(cache);
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
            node.prev = null;
            node.next = null;
        }

        Node first() {
            return isEmpty() ? null : head.next;
        }

        boolean isEmpty() {
            return head.next == tail;
        }

        @Override
        public String toString() {

            Node current = tail.prev;
            StringBuilder sb = new StringBuilder();

            while (current != head) {
                sb.append(current);
                current = current.prev;
            }

            return sb.toString();
        }

        static class Node {

            int key;
            int value;
            Node prev;
            Node next;
            int frequency;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                frequency = 1;
                prev = null;
                next = null;
            }

            @Override
            public String toString() {
                return "(" + key + ", " + value + ")";
            }
        }
    }
}
```

## Java Solution

- inspired by java solution of [LRU Cache](./LRU%20Cache.md)

```java
class LFUCache {

    private final int capacity;
    private final Map<Integer, Map<Integer, Integer>> lookup;
    private final Map<Integer, Integer> frequencyLookup;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        lookup = new HashMap<>();
        frequencyLookup = new HashMap<>();
        minFrequency = 0;
    }

    public int get(int key) {

        if (!frequencyLookup.containsKey(key)) {
            return -1;
        }

        incrementFrequencyOfKey(key);

        int frequency = frequencyLookup.get(key);
        return lookup.get(frequency).get(key);
    }

    private void incrementFrequencyOfKey(int key) {

        int frequency = frequencyLookup.get(key);
        int value = lookup.get(frequency).get(key);

        // 1 - update frequencyLookup
        frequencyLookup.put(key, frequency + 1);
        
        // 2 - lookup - remove old frequency references
        lookup.get(frequency).remove(key);
        if (lookup.get(frequency).size() == 0) {

            lookup.remove(frequency);

            if (minFrequency == frequency) {
                minFrequency += 1;
            }
        }

        // 3 - lookup - add new frequency
        if (!lookup.containsKey(frequency + 1)) {
            lookup.put(frequency + 1, new LinkedHashMap<>());
        }
        lookup.get(frequency + 1).put(key, value);
    }

    public void put(int key, int value) {

        if (frequencyLookup.containsKey(key)) {
            incrementFrequencyOfKey(key);
            int frequency = frequencyLookup.get(key);
            lookup.get(frequency).put(key, value);
            return;
        }

        if (frequencyLookup.size() == capacity) {
            int keyToEject = lookup.get(minFrequency).keySet().iterator().next();
            frequencyLookup.remove(keyToEject);
            lookup.get(minFrequency).remove(keyToEject);
        }

        minFrequency = 1;
        frequencyLookup.put(key, minFrequency);
        
        if (!lookup.containsKey(minFrequency)) {
            lookup.put(minFrequency, new LinkedHashMap<>());
        }

        lookup.get(minFrequency).put(key, value);
    }
}
```
