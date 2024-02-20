# Non-overlapping Intervals

- https://leetcode.com/problems/non-overlapping-intervals/
- two intervals overlap when start of one is lesser than end of other
- the one ending later can overlap with more intervals
- so, we record the end time of the last interval we included in our answer
- any subsequent intervals which overlap with it are dropped
- otherwise we update the end time

```java
class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {

        intervals = Arrays.copyOfRange(intervals, 0, intervals.length);
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int result = 0;
        int lastIncludedIntervalsEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (lastIncludedIntervalsEnd > intervals[i][0]) {
                result += 1;
            } else {
                lastIncludedIntervalsEnd = intervals[i][1];
            }
        }

        return result;
    }
}
```
