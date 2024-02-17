# Maximum Sum Combinations

- https://www.interviewbit.com/problems/maximum-sum-combinations/
- first, we sort the lists
- if (i, j) is a part of the result
- either (i+1, j) or (i, j+1) should be a part of the result as well
- also, greatest possible will of course be (N-1, N-1)
- so, we just add (N-1, N-1) to the max heap
- for every pair we pop from the max heap, we add (i-1, j), (i, j-1) to the priority queue
- we also need to maintain a "seen" boolean map to track whether a pair has already been seen or not
- otherwise for e.g. both (i-1,j) and (i,j-1) will end up adding (i-1,j-1) - thus resulting in duplicates
- since we do not have pair class in java out of the box, `List` works just fine

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Solution {

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {

        Collections.sort(A);
        Collections.sort(B);

        Set<List<Integer>> usedPairs = new HashSet<>();
        PriorityQueue<MaxHeapNode> maxHeap = new PriorityQueue<>((a, b) -> b.sum - a.sum);

        addPair(A, B, A.size() - 1, B.size() - 1, usedPairs, maxHeap);

        ArrayList<Integer> result = new ArrayList<>();

        while (result.size() < C) {

            MaxHeapNode maxHeapNode = maxHeap.remove();
            result.add(maxHeapNode.sum);

            if (maxHeapNode.indices.get(0) > 0) {
                addPair(A, B, maxHeapNode.indices.get(0) - 1, maxHeapNode.indices.get(1), usedPairs, maxHeap);
            }

            if (maxHeapNode.indices.get(1) > 0) {
                addPair(A, B, maxHeapNode.indices.get(0), maxHeapNode.indices.get(1) - 1, usedPairs, maxHeap);
            }
        }

        return result;
    }

    private void addPair(List<Integer> A, List<Integer> B, int aIdx, int bIdx,  Set<List<Integer>> usedPairs, PriorityQueue<MaxHeapNode> maxHeap) {
        List<Integer> indices = new ArrayList<>();
        indices.add(aIdx); indices.add(bIdx);
        if (!usedPairs.contains(indices)) {
            usedPairs.add(indices);
            maxHeap.add(new MaxHeapNode(A.get(indices.get(0)) + B.get(indices.get(1)), indices));
        }
    }

    static class MaxHeapNode {

        int sum;
        List<Integer> indices;

        MaxHeapNode(int sum, List<Integer> indices) {
            this.sum = sum;
            this.indices = indices;
        }
    }
}
```
