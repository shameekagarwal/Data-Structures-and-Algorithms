- big o = "time complexity"
- measured in terms of "number of operations"
- another metric - "space complexity"
- greek symbols - 
  - best case - omega
  - average case - theta
  - worst case - omicron (big o)
- e.g. we print n numbers - it takes n operations. on a graph, o(n) would be linear - time complexity increases linearly wrt n
- o(2 * n) = o(n) - we drop constants in big o notation
- a nested loop - o(n^2)
- drop non dominants - o(n^2 + n) = o(n ^ 2), since n^2 grows much faster as compared to n
- o(1) - constant time
- o(log(n)) - binary search (divide and conquer)
- o(n * log(n)) - sorting algorithms
- if we have two loops of different variables, complexity is not o(2 * n) = o(n), it is o(a + b)
- adding, removing, searching for an element in list - o(n)
- accessing elements via index in list - o(1)
- object reference vs primitive - remember how object reference acts as a pointer
  ```
  ref1 = ref2
  ref2.update(new_val) // ref1 changes as well

  prm1 = prm2
  prm2 = new_val // prm1 stays the same
  ```
- amortized - when for e.g. sometimes some operations take a lot of time, but that does not happen often, so we try averaging out the cost. example is "Implement Queue using Stacks" under "Stacks & Queues"
