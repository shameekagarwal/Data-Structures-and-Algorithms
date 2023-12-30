# Solution

```java
public ArrayList<String> keys() {
    ArrayList<String> allKeys = new ArrayList<>();
    for (int i = 0; i < size; i++) {
        Node temp = dataMap[i];
        while (temp != null) {
            allKeys.add(temp.key);
        }
    }
    return allKeys;
}
```
