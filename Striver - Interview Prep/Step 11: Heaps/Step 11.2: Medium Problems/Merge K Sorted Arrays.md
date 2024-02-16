# Merge K Sorted Arrays

- https://www.codingninjas.com/studio/problems/merge-k-sorted-arrays_975379
- inspired ditto by [Flattening of LL](/Striver%20-%20Interview%20Prep/Step%206:%20Learn%20LinkedList/Step%206.5/Flattening%20of%20LL.md)
- but there, we had the liberty of using `.child`, so we had the liberty of storing just the node and the value. here, we need to store "which list" and "which index" as well to be able to grab the next element

```java
import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

public class Solution {

	public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> kArrays, int k) {

		ArrayList<Integer> merged = new ArrayList<>();
		PriorityQueue<PQNode> minHeap = new PriorityQueue<>();

		for (int i = 0; i < kArrays.size(); i++) {
			if (kArrays.get(i).size() > 0) {
				minHeap.add(new PQNode(i, 0, kArrays.get(i).get(0)));
			}
		}

		while (!minHeap.isEmpty()) {
			
			PQNode minPQNode = minHeap.remove();
			merged.add(minPQNode.element);

			if (minPQNode.listIdx < kArrays.get(minPQNode.listId).size() - 1) {
				minHeap.add(new PQNode(minPQNode.listId, minPQNode.listIdx + 1, kArrays.get(minPQNode.listId).get(minPQNode.listIdx + 1)));
			}
		}

		return merged;
	}

	static class PQNode implements Comparable<PQNode> {

		int listId;
		int listIdx;
		int element;

		PQNode(int listId, int listIdx, int element) {
			this.listId = listId;
			this.listIdx = listIdx;
			this.element = element;
		}

		@Override
		public int compareTo(PQNode pqNode) {
			return element - pqNode.element;
		}

		@Override
		public String toString() {
			return "(listId: " + listId + ", listIdx: " + listIdx + ", element: " + element + ")";
		}
	}
}
```
