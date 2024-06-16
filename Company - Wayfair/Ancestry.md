# Description

All In The Family: Suppose we have input data describing relationships between parents and children over multiple generations. The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.

For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4:

```
		     10 
		     / 
   1    2      4
    \  /     /   \
     3      5     8
	    \    /  \     \
	     \  /    \     \
		    6       7     9
```


**Find the Earliest Ancestor**

Write a function that, for a given individual in our dataset, returns their earliest known ancestor – the one at the farthest distance from the input individual. If there is more than one ancestor tied for “earliest”, return any one of them. If the input individual has no parents, the function should return null (or -1).

# Sample input and output:

parentChildPairs = [[1, 3], [2, 3], [3, 6], [5, 6], [5, 7], [4, 5], [4, 8], [8, 9], [10,2]];

8 => 4
7 => 4
6 => 10

# Common Clarifications

* Any member of the “earliest” generation of the input individual’s ancestors is an acceptable result.
* No person will appear in the ancestor list twice (via two different relationship paths).
* There are no cycles in the input.
 