# Time Complexity

- actual time taken by a code is not time complexity - that is dependent on several factors like machine capacity
- time complexity - rate at which time taken increases wrt input size
- big o notation (O) is used
- rules of big o - 
  - worst case scenario is considered
  - avoid constants
  - avoid lower values - O(n^3 + n^2) = O(n^3)
- theta notation - average complexity
- omega notation - lower bound
- e.g. for below, complexity is - 0 + 1 + ... n - 1 = ((n - 1) * n) / 2 = O(n^2)
  ```java
  for (int i = 0; i < n; i++) {
    for (int j = 0; j <= i; j++) {
      // ...
    }
  }
  ```
- space complexity - auxiliary space + input space
  - auxiliary space - space to solve the problem
  - input space - space to store the input
- usually systems take - 1s for 10^8 operations
- so, 2s for 2*10^8 operations
