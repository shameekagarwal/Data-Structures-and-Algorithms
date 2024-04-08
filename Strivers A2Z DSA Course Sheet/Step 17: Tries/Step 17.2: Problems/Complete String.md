# Complete String

- https://www.naukri.com/code360/problems/complete-string_2687860
- first insert all strings into the trie
- then, traverse the trie for each string. for each iteration, check if end flag is true
- if not, it means that the particular prefix is not present as a word in the trie

```java
import java.util.* ;
import java.io.*; 

class Solution {

  public static String completeString(int n, String[] a) {
    
    Node head = new Node();
    char[][] arr = new char[n][];

    for (int i = 0; i < n; i++) {
      arr[i] = a[i].toCharArray();
    }

    for (int i = 0; i < n; i++) {

      Node current = head;

      for (char c : arr[i]) {

        if (current.ptr[c - 'a'] == null) {
          current.ptr[c - 'a'] = new Node();
        }

        current = current.ptr[c - 'a'];
      }

      current.end = true;
    }

    String ans = "";

    for (int i = 0; i < n; i++) {

      Node current = head;
      boolean prefixExists = true;

      for (char c : arr[i]) {
        current = current.ptr[c - 'a'];
        prefixExists = prefixExists & current.end;
      }

      boolean isLonger = ans.length() < a[i].length();
      boolean isLexicographicallySmaller = (ans.length() == a[i].length()) && (ans.compareTo(a[i]) > 0);

      if (prefixExists && (isLonger || isLexicographicallySmaller)) {
        ans = a[i];
      }
    }

    return ans.equals("") ? "None" : new String(ans);
  }

  static class Node {

    Node[] ptr;
    boolean end;

    Node() {
      ptr = new Node['z' - 'a' + 1];
      end = false;
    }
  }
}
```
