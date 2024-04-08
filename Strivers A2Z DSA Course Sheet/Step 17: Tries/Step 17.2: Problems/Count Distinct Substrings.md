# Count Distinct Substrings

- https://www.naukri.com/code360/problems/count-distinct-substrings_985292
- mark end of head as true and start with result = 1
- for every substring (use the standard way - j = i to n)
  - if end has not already been marked - mark end as true and increment result by 1
  - if end has already been marked - this substring has already been seen in the past

```java
import java.util.ArrayList;

public class Solution {

	public static int countDistinctSubstrings(String s) {

		Node head = new Node();
		head.end = true;

		char[] word = s.toCharArray();
		int n = word.length;

		int result = 1;

		for (int i = 0; i < n; i++) {

			Node current = head;

			for (int j = i; j < n; j++) {

				if (current.ptr[word[j] - 'a'] == null) {
					current.ptr[word[j] - 'a'] = new Node();
				}

				current = current.ptr[word[j] - 'a'];
				if (!current.end) {
					current.end = true;
					result += 1;
				}
			}
		}

		return result;
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
