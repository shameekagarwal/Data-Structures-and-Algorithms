# Count Distinct Substrings

- https://www.naukri.com/code360/problems/count-distinct-substrings_985292
- mark end of head as true and start with result = 1
- for every substring (use the standard way - j = i to n)
  - if end has not already been marked - mark end as true and increment result by 1
  - if end has already been marked - this substring has already been seen in the past

```java
import java.util.*;

public class Solution  {

	public static int countDistinctSubstrings(String s) {

		char[] arr = s.toCharArray();

		Trie trie = new Trie();
		int result = 1;

		for (int i = 0; i < s.length(); i++) {

			for (int j = i; j < s.length(); j++) {
				result += trie.insert(arr, i, j) ? 1 : 0;
				// System.out.println(i + ", " + j + ": " + result);
			}

			// System.out.println();
		}

		return result;
	}

	static class Trie {

		Node head = new Node();

		private boolean insert(char[] arr, int start, int end) {

			Node current = head;
			boolean exists = true;

			for (int i = start; i <= end; i++) {

				if (!current.ptr.containsKey(arr[i])) {
					exists = false;
					current.ptr.put(arr[i], new Node());
				}

				current = current.ptr.get(arr[i]);
			}

			return !exists;
		}

		static class Node {

			public Map<Character, Node> ptr = new HashMap<>();
		}
	}
}
```
