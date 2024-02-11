# Merge Intervals

- https://leetcode.com/problems/merge-intervals/
- sort first
- if last added intervals end >= incoming intervals start
- then, interval can be "merged" - last added interval's end = max(incoming intervals end, last added interval's end)
- vvimp - look at the custom comparator that we implemented

```java
class Solution {

    public int[][] merge(int[][] intervalsRaw) {
        
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < intervalsRaw.length; i++) {
            intervals.add(intervalsRaw[i]);
        }
        Collections.sort(intervals, this::compareInterval);
        
        List<int[]> result = new ArrayList<>();
        for (int[] it : intervals) {
            if (!result.isEmpty()) {
                int[] lastAddedInterval = result.get(result.size() - 1);
                if (lastAddedInterval[1] >= it[0]) {
                    lastAddedInterval[1] = Math.max(lastAddedInterval[1], it[1]);
                } else {
                    result.add(it);
                }
            } else {
                result.add(it);
            }
        }

        int[][] resultArray = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i][0] = result.get(i)[0];
            resultArray[i][1] = result.get(i)[1];
        }

        return resultArray;
    }

    private Integer compareInterval(int[] pairA, int[] pairB) {
        int firstIntervalComparison = pairA[0] - pairB[0];
        int secondIntervalComparison = pairA[1] - pairB[1];
        return firstIntervalComparison == 0 ? secondIntervalComparison : firstIntervalComparison;
    }
}
```
