# Minimum Spanning Tree

- applicable for "undirected weighted graph"
- spanning tree 
  - we have n nodes and n - 1 edges
  - all nodes are reachable from each other
- minimum spanning tree - tree with minimum sum
- so, we will be given a graph, and we have to remove edges from it to convert it to an mst
- two algorithms for it - prims and kruskals

```
[0]---2--[1]--3---[2]
 |       /|       /
 |      / |      /
 |     /  |     /
 6    8   5    7
 |   /    |   /
 |  /     |  /
 | /      | /
[3]      [4]
```

```
# Sum = 16 - MST               # Sum = 20 

[0]---2--[1]--3---[2]          [0]---2--[1]--3---[2]
 |        |                             /        /
 |        |                            /        /
 |        |                           /        /
 6        5                          8        7
 |        |                         /        /
 |        |                        /        /
 |        |                       /        /
[3]      [4]                    [3]      [4]
```
