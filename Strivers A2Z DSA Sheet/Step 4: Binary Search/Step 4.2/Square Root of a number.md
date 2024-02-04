# Square Root of a number

- https://www.codingninjas.com/studio/problems/square-root-integral_893351

```java
import java.util.* ;
import java.io.*; 

public class Solution {

	public static int sqrtN(long N) {
		
		int ans = (int) N;
		int low = 0;
		int high = (int) N;

		while (low <= high) {
			int mid = low + ((high - low) / 2);
			if ((long) mid * mid <= N) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return ans;
	}
}

```
