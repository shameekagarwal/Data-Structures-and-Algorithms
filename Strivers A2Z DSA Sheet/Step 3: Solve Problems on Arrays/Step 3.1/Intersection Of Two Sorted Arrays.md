# Intersection Of Two Sorted Arrays

- https://www.codingninjas.com/studio/problems/intersection-of-2-arrays_1082149
- note - duplicates are allowed here. note how is different from [Find the Union](./Find%20the%20Union.md) in the same section

```java
import java.util.* ;
import java.io.*;

public class Solution {
	public static ArrayList<Integer> findArrayIntersection(ArrayList<Integer> arr1, int n, ArrayList<Integer> arr2, int m) {
		
		ArrayList<Integer> intersection = new ArrayList<>();
		
		int pointerArr1 = 0;
		int pointerArr2 = 0;
		
		while (pointerArr1 < arr1.size() && pointerArr2 < arr2.size()) {
			if (arr1.get(pointerArr1) < arr2.get(pointerArr2)) {
				pointerArr1 += 1;
			} else if (arr2.get(pointerArr2) < arr1.get(pointerArr1)) {
				pointerArr2 += 1;
			} else {
				intersection.add(arr1.get(pointerArr1));
				pointerArr1 += 1;
				pointerArr2 += 1;
			}
		}
		
		return intersection;
	}
}
```
