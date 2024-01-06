# Check Prime

- https://www.codingninjas.com/studio/problems/check-prime_624934
- note - java was not working

```cpp
bool isPrime(int n)
{
	if (n == 1) return false;
	for (int i = 2; i * i <= n; i++) {
		if (n % i == 0) return false;
	}
	return true;
}
```
