# Solution

```java
public int get(String key) {
    int keyHash = hash(key);
    Node temp = dataMap[keyHash];
    while (temp != null) {
        if (temp.key == key) {
            return temp.value;
        }
        temp = temp.next;
    }
    return 0;
}
```
