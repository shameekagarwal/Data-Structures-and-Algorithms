# The Celebrity Problem

- https://www.codingninjas.com/studio/problems/the-celebrity-problem_982769
- there can be be either one celebrity or no celebrities - not more than one celebrity
- we assume celebrity to be 0 at beginning
- then, we iterate through the remaining elements
- if the current element is a celebrity, then the assumed celebrity will know it

```java
public class Solution {
	public static int findCelebrity(int n) {
		
		int celebrity = 0;
		
		for (int i = 1; i < n; i++) {
			if (Runner.knows(celebrity, i)) {
				celebrity = i;
			}
		}

		for (int i = 0; i < n; i++) {
			if (celebrity != i && (Runner.knows(celebrity, i) || !Runner.knows(i, celebrity))) {
				return -1;
			}
		}

		return celebrity;
    }
}
```
