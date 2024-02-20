# Insert Interval

- https://leetcode.com/problems/insert-interval/
- there are too many cases too handle, so i cme up with this
- add all intervals with end strictly less than new interval
- add new interval
- merge all incoming intervals - we need to merge if the start of this interval is less than equal to end of last calculated interval. this logic is same as [Merge Intervals](../../Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.3:%20Hard/Merge%20Intervals.md)
- add the remaining non overlapping intervals

```java
class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int i = 0;
        Deque<int[]> stack = new ArrayDeque<>();

        // add the existing non overlapping intervals
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            stack.addLast(intervals[i]);
            i += 1;
        }

        // add the new interval
        stack.addLast(newInterval);

        // merge into the existing new interval
        while (i < intervals.length && stack.peekLast()[1] >= intervals[i][0]) {
            stack.peekLast()[0] = Math.min(stack.peekLast()[0], intervals[i][0]);
            stack.peekLast()[1] = Math.max(stack.peekLast()[1], intervals[i][1]);
            i += 1;
        }

        // add the existing non overlapping intervals
        while (i < intervals.length) {
            stack.addLast(intervals[i]);
            i += 1;
        }

        int[][] result = new int[stack.size()][];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            result[idx] = stack.removeLast();
            idx -= 1;
        }
        return result;
    }
}
```
