# Check Armstrong

- https://www.codingninjas.com/studio/problems/check-armstrong_589

```java
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		// Write your code here
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		
		long temp = n;
		int numOfDigits = 0;
		while (temp > 0) {
			numOfDigits += 1;
			temp /= 10;
		}

		temp = n;
		long result = 0;
		while (temp > 0) {
			long digit = temp % 10;
			result += Math.pow(digit, numOfDigits);
			temp /= 10;
		}
		System.out.println(n == result);
	}
}
```
