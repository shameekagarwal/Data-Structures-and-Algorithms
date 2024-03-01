# Partitions With Given Difference

- https://www.codingninjas.com/studio/problems/partitions-with-given-difference_3751628
- s1 - s2 = d
- s1 + s2 = s
- s1 = (s + d) / 2
- remember to check s + d is divisible by 2
- now, simply use [Subset Sum Equal To K](./Subset%20Sum%20Equal%20To%20K.md), where k is s1

```java
import java.util.*;
import java.io.*;

public class Solution {

	private static final int MOD = 1000000007;

	public static int countPartitions(int n, int d, int[] arr) {

		int target = 0;
		for (int i : arr) {
			target += i;
		}
		target += d;
		if (target % 2 != 0) return 0;
		target /= 2;

		int[] previous = new int[target + 1];
		previous[0] = 1;

		for (int i : arr) {
			
			int[] current = new int[target + 1];
			
			for (int j = 0; j <= target; j++) {
				current[j] = previous[j];
				if (i <= j) {
					current[j] = (current[j] + previous[j - i]) % MOD;
				}
			}
			
			previous = current;
 		}

		return previous[target];
	}
}
```
