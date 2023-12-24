# Solution

```java
public void set(String key, int value) {
    Node newNode = new Node(key, value);
    int keyHash = hash(key);
    if (dataMap[keyHash] == null) {
        dataMap[keyHash] = newNode;
    } else {
        Node temp = dataMap[keyHash];
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }
}
```
