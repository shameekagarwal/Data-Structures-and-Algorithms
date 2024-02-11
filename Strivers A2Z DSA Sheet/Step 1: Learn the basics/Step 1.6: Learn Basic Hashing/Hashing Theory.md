# Hashing Theory

- how many times an element occurs in an array - O(q * n) 
  - q -  number of times this question is asked
  - n - size of array
- can use an array as a hash map if numbers ~ 10^6
- for lower case characters,  we can do char - 'a', and use an array of length 26 elements
- char in cpp etc is ascii, so 256
- char in java is unicode, so 2^16 = 65536
- so for interviews, safe to declare an array of 65536
- what if limit is not feasible, e.g. int is 10^9 - we cannot use array - use collections
- recall java implementations, and recall the differences - tree map - O(log n), linked hash map - O(1), sometimes O(n), hash map - O(1), sometimes O(n)
- implementing a naive map - if size is n, use array of size n, where each element is a list. list because of collision
