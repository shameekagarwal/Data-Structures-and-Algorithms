# Points

- navigate till current is null
- if current is less than value to find, go right
- if current is greater than value to find, go left

# Solution

```java
public boolean contains(int value) {
    Node temp = root;
    while (temp != null) {
        if (temp.value == value) return true;
        else if (temp.value < value) temp = temp.right;
        else temp = temp.left;
    }
    return false;
}
```
