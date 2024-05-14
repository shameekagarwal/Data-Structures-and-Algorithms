# Implement Trie (Prefix Tree)

- https://leetcode.com/problems/implement-trie-prefix-tree/
- query - insert word, is there any word with the same word / the same prefix

```java
class Trie {

    Node head;

    public Trie() {
        head = new Node();
    }

    public void insert(char[] word) {

        Node current = head;

        for (int i = 0; i < word.length; i++) {
            
            if (!current.ptr.containsKey(word[i])) {
                current.ptr.put(word[i], new Node());
            }
            
            current = current.ptr.get(word[i]);
        }

        current.isEnd = true;
    }

    private Node find(char[] word) {

        Node current = head;

        for (int i = 0; i < word.length; i++) {
            
            if (!current.ptr.containsKey(word[i])) {
                return null;
            }
            
            current = current.ptr.get(word[i]);
        }

        return current;
    }
    
    public boolean search(char[] word) {
        Node node = find(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(char[] prefix) {
        Node node = find(prefix);
        return node != null;
    }
    
    public void insert(String word) {
        insert(word.toCharArray());
    }
    
    public boolean search(String word) {
        return search(word.toCharArray());
    }
    
    public boolean startsWith(String prefix) {
        return startsWith(prefix.toCharArray());
    }

    static class Node {

        Map<Character, Node> ptr = new HashMap<>();
        boolean isEnd = false;
    }
}
```
