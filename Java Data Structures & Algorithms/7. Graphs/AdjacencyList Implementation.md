# Starting Code

```java
public class Graph {

    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public HashMap<String, ArrayList<String>> getAdjList() {
        return adjList;
    }

    public void printGraph() {
        System.out.println(adjList);
    } 
}
```

# Add a Vertex

```java
public boolean addVertex(String vertex) {
    if (adjList.containsKey(vertex)) return false;
    adjList.put(vertex, new ArrayList<>());
    return true;
}
```

# Add an Edge

```java
public boolean addEdge(String vertex1, String vertex2) {
    if (!adjList.containsKey(vertex1) || !adjList.containsKey(vertex2)) return false;
    adjList.get(vertex1).add(vertex2);
    adjList.get(vertex2).add(vertex1);
    return true;
}
```

# Remove an Edge

```java
public boolean removeEdge(String vertex1, String vertex2) {
    if (!adjList.containsKey(vertex1) || !adjList.containsKey(vertex2)) return false;
    adjList.get(vertex1).remove(vertex2);
    adjList.get(vertex2).remove(vertex1);
    return true;
}
```

# Remove a Vertex

```java
public boolean removeVertex(String vertex) {
    if (!adjList.containsKey(vertex)) return false;
    adjList.get(vertex)
        .forEach((vertexTo) -> adjList.get(vertexTo).remove(vertex));
    adjList.remove(vertex);
    return true;
}
```
