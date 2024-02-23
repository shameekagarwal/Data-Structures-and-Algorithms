# Graph Representation | Java

- 1st line - n and m
- n - number of nodes, m - number of edges
- now, m lines where each line has the from and to
- we can use adjacency matrix - disadvantage it occupies a lot of memory
  ```java
  boolean[][] mat = new boolean[n + 1][n + 1];

  mat[u][v] = 1;
  mat[v][u] = true; // if undirected
  ```
- for weights - maybe we can use int instead, and the value will represent the weight
- adjacency list - 
  ```java
  List<List<Integer>> list = new ArrayList<>();
  // Collections.nCopies will not work - the same array list reference gets used for all vertices
  for (int i = 0; i < N + 1; i++) {
    list.add(new ArrayList<>());
  }
  list.get(u).add(v);
  list.get(v).add(u); // if undirected
  ```
- for weights - maybe we can use `int[]` instead of `Integer`
