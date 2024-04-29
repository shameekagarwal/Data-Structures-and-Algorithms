# Replace Each Element Of Array With Its Corresponding Rank

- https://www.codingninjas.com/studio/problems/replace-each-element-of-array-with-its-corresponding-rank_975384
- brute - sort elements - first we need to form a node which tracks both indices and the element. complexity - O(n * logN)
- space complexity - O(n)
- make note of stl - instead of using `Comparable` like in [this question](./Merge%20K%20Sorted%20Arrays.md), we pass a lambda - advantage - can allow for more flexibility. my assumption - this lambda is a shorthand of `Comparator`?
- make note of stl - initializing a list with a specific size and an element - `new ArrayList<>(Collections.nCopies(size, initial_element));`
- note, my understanding - in lists etc - capacity != size. size is what we did above, but we can also use `new ArrayList<>(10)` - this is providing an initial capacity. i think this is to do with the dynamic resizing capability of lists, this has nothing to do with the size we are interested in
- complexity ≈ arguable, might be O(n) or O(n*logN), not sure

```java
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

public class Solution {
    
    public static List<Integer> replaceWithRank(List<Integer> arr, int n) {

        PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>((a, b) -> a.element - b.element);
        List<Integer> rank = new ArrayList<>(Collections.nCopies(arr.size(), -1));

        for (int i = 0; i < arr.size(); i++) {
            minHeap.add(new MinHeapNode(i, arr.get(i)));
        }

        int previousElement = Integer.MIN_VALUE;
        int currentRank = 0;

        while (!minHeap.isEmpty()) {
            MinHeapNode minHeapNode = minHeap.remove();
            if (minHeapNode.element != previousElement) {
                previousElement = minHeapNode.element;
                currentRank += 1;
            }
            rank.set(minHeapNode.index, currentRank);
        }

        return rank;
    }

    static class MinHeapNode {

        int index;
        int element;

        MinHeapNode(int index, int element) {
            this.index = index;
            this.element = element;
        }

        @Override
        public String toString() {
            return "(index: " + index + ", element: " + element + ")";
        }
    }
}
```

## Solution 2

- tree set - sort + unique elements
- map - calculate rank by iterating through this tree set serially
- finally, just use the map lookup to find rank for every index

```java
import java.util.*;

public class Solution {

    public static List<Integer> replaceWithRank(List<Integer> arr, int n) {

        Set<Integer> set = new TreeSet<>();

        for (int i : arr) {
            set.add(i);
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : set) {
            map.put(i, map.size() + 1);
        }

        List<Integer> result = new ArrayList<>();

        for (int i : arr) {
            result.add(map.get(i));
        }

        return result;
    }
}
```
