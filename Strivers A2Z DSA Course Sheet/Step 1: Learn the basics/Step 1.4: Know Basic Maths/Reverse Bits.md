# Reverse Bits

- https://www.codingninjas.com/studio/problems/reverse-bits_2181102

```java
public class Solution {

	public static long reverseBits(long n) {
		long result = 0;
		for (int i = 0; i < 32; i++) {
			result = (result * 2) + (n % 2);
			n /= 2;
		}
		return result;
	}
}
```
