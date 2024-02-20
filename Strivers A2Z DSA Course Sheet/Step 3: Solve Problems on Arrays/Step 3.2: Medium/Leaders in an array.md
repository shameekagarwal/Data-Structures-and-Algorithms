# Leaders in an array

- https://www.codingninjas.com/studio/problems/leaders-in-an-array_873144
- remember `Collections.reverse` stl

```java
import java.util.* ;
import java.io.*;

public class Solution {

	public static ArrayList<Integer> findLeaders(ArrayList<Integer> elements, int n) {
		
		ArrayList<Integer> leaders = new ArrayList<>();
		int maxTillNow = Integer.MIN_VALUE;
		
		for (int i = elements.size() - 1; i > -1; i--) {
			if (elements.get(i) > maxTillNow) {
				maxTillNow = elements.get(i);
				leaders.add(elements.get(i));
			}
		}
		
		Collections.reverse(leaders);
		return leaders;
	}
}
```
