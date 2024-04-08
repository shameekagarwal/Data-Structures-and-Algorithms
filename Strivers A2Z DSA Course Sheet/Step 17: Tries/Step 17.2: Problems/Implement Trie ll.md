# Implement Trie ll

- https://www.naukri.com/code360/problems/implement-trie_1387095
- need to maintain count and end count as well
- now, we do not need the boolean end flag anymore - end count does the job

```java
import java.util.*;
import java.io.*;

public class Trie {

    private Node head;

    public Trie() {
        head = new Node();
    }

    public void insert(String word) {
        
        Node current = head;
        char[] arr = word.toCharArray();

        for (char c : arr) {
            if (current.ptr[c - 'a'] == null) {
                current.ptr[c - 'a']  = new Node();
            }
            current = current.ptr[c - 'a'];
            current.count += 1;
        }

        current.endCount += 1;
    }

    public int countWordsEqualTo(String word) {

        Node current = head;
        char[] arr = word.toCharArray();

        for (char c : arr) {
            current = current.ptr[c - 'a'];
            if (current == null || current.count == 0) return 0;
        }

        return current.endCount;
    }

    public int countWordsStartingWith(String word) {

        Node current = head;
        char[] arr = word.toCharArray();

        for (char c : arr) {
            current = current.ptr[c - 'a'];
            if (current == null || current.count == 0) return 0;
        }

        return current.count;
    }

    public void erase(String word) {

        char[] arr = word.toCharArray();
        Node current = head;

        for (char c : arr) {
            current = current.ptr[c - 'a'];
            current.count -= 1;
        }

        current.endCount -= 1;
    }

    static class Node {

        Node[] ptr;
        int endCount;
        int count;

        Node() {
            ptr = new Node['z' - 'a' + 1];
            endCount = 0;
            count = 0;
        }
    }
}
```
