# Data Stream As Disjoint Intervals

- https://leetcode.com/problems/data-stream-as-disjoint-intervals/

## TreeSet

- add - O(logN) - because bst like structure underneath
- get intervals - O(N) - iterate one by one - this will be in order. then, accordingly form intervals
- vvimp - see `toArray` function to easily convert list to array

```java
class SummaryRanges {

    private Set<Integer> set;

    public SummaryRanges() {
        set = new TreeSet<>();
    }
    
    public void addNum(int value) {
        set.add(value);
    }
    
    public int[][] getIntervals() {
        
        int currentIntervalStart = -2;
        int currentIntervalEnd = -2;

        List<int[]> intervals = new ArrayList<>();

        for (int element : set) {
            if (element == currentIntervalEnd + 1) {
                currentIntervalEnd = element;
            } else {
                if (currentIntervalStart != -2) {
                    intervals.add(new int[]{currentIntervalStart, currentIntervalEnd});
                }
                currentIntervalStart = element;
                currentIntervalEnd = element;
            }
        }

        if (currentIntervalStart != -2) {
            intervals.add(new int[]{currentIntervalStart, currentIntervalEnd});
        }

        return intervals.toArray(new int[][]{});
    }
}
```

## DSU

- if parent of node is -1 - it has not been visited
- parent of all nodes is -1
- when calling add, call union(val - 1, val) and union(val + 1, val)
- both parent and union have checks for -1, otherwise implementation is same as [this](./Disjoint%20Set.md)

```java
class SummaryRanges {

    private int[] parent;
    private int[] rank;

    public SummaryRanges() {

        parent = new int[10005];
        Arrays.fill(parent, -1);

        rank = new int[10005];
    }

    private int findParent(int node) {

        if (parent[node] == -1) {
            return -1;
        }

        if (node == parent[node]) {
            return node;
        }

        parent[node] = findParent(parent[node]);
        return parent[node];
    }

    private void union(int a, int b) {

        int parentA = findParent(a);
        int parentB = findParent(b);

        if (parentA == -1 || parentB == -1) return;

        if (rank[parentA] < rank[parentB]) {
            union(b, a);
        } else {
            if (rank[parentA] == rank[parentB]) {
                rank[parentA] += 1;
            }
            parent[parentB] = parentA;
        }
    }

    public void addNum(int value) {

        if (parent[value] == -1) {
            parent[value] = value;
            rank[value] = 0;
        }

        if (value != 0) {
            union(value, value - 1);
        }
        union(value, value + 1);
    }
    
    public int[][] getIntervals() {

        Deque<int[]> intervals = new ArrayDeque<>();

        for (int i = 0; i < parent.length; i++) {
 
            if (parent[i] == -1) continue;

            if (!intervals.isEmpty() && intervals.peekLast()[1] == i - 1) {
                intervals.peekLast()[1] = i;
            } else {
                intervals.addLast(new int[]{i, i});
            }
        }

        int[][] result = new int[intervals.size()][];
        int i = 0;
        while (!intervals.isEmpty()) {
            result[i] = intervals.removeFirst();
            i += 1;
        }
        return result;
    }
}
```
