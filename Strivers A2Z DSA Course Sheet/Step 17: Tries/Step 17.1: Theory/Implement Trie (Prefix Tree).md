# Implement Trie (Prefix Tree)

- https://leetcode.com/problems/implement-trie-prefix-tree/
- query - insert word, is there any word with the same word / the same prefix

```java
class Trie {

    static class Node {

        Node[] ptr;
        boolean end;

        Node() {
            ptr = new Node['z' - 'a' + 1];
            end = false;
        }
    }

    Node head;

    public Trie() {
        head = new Node();
    }

    public void insert(String word) {

        char[] characters = word.toCharArray();
        Node current = head;

        for (char c : characters) {
            if (current.ptr[c - 'a'] == null) {
                current.ptr[c - 'a'] = new Node();
            }
            current = current.ptr[c - 'a'];
        }

        current.end = true;
    }

    public boolean search(String word) {

        char[] characters = word.toCharArray();
        Node current = head;

        for (char c : characters) {
            if (current.ptr[c - 'a'] == null) return false;
            current = current.ptr[c - 'a'];
        }

        return current.end;
    }

    public boolean startsWith(String prefix) {

        char[] characters = prefix.toCharArray();
        Node current = head;

        for (char c : characters) {
            if (current.ptr[c - 'a'] == null) return false;
            current = current.ptr[c - 'a'];
        }

        return true;
    }
}
```
