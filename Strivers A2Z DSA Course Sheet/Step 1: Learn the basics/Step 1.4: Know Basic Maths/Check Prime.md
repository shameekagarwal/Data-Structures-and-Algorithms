# Check Prime

- https://www.codingninjas.com/studio/problems/check-prime_624934
- note - java was not working
- checking 2 and odd numbers only up to sqrt(n)
- remember boundary cases for 1 and 2

```cpp
bool isPrime(int n)
{
	if (n == 1) return false;
	if (n == 2) return true;
	if (n % 2 == 0) return false;
	for (int i = 3; i * i <= n; i += 2) {
		if (n % i == 0) return false;
	}
	return true;
}
```
