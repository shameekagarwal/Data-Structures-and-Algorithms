# Longest Substring with At Most K Distinct Characters

- we find largest substring possible ending at r
- move l forward till window contains > k characters
- result = max(result, r - l + 1)
- https://www.codingninjas.com/studio/problems/longest-substring-with-at-most-k-distinct-characters_2221410

```java
import java.util.Map;
import java.util.HashMap;

public class Solution {

	public static int kDistinctChars(int k, String str) {
		
		Map<Character, Integer> frequency = new HashMap<>();
		char[] characters = str.toCharArray();

		int l = 0;
		int result = -1;
		
		for (int r = 0; r < characters.length; r++) {
			
			frequency.put(characters[r], frequency.getOrDefault(characters[r], 0) + 1);
			
			while (frequency.size() > k) {
				if (frequency.containsKey(characters[l])) {
					if (frequency.get(characters[l]) == 1) {
						frequency.remove(characters[l]);
					} else {
						frequency.put(characters[l], frequency.get(characters[l]) - 1);
					}
				}
				l += 1;
			}

			result = Math.max(result, r - l + 1);
		}

		return result;
	}
}

```
