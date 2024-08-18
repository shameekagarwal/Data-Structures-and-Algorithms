# Meeting Rooms II

- https://leetcode.com/problems/meeting-rooms-ii/
- add 1 for incoming event of a meeting, subtract 1 when meting is over
- boundary case to ask interviewer - if a meeting ends at 10 and another starts at 10, we need to ask if 2 meeting rooms are required or 1 is sufficient

```java
class Solution {

    public int minMeetingRooms(int[][] intervals) {

        int n = intervals.length;
        int[][] arr = new int[2 * n][];

        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{intervals[i][0], 1};
            arr[n + i] = new int[]{intervals[i][1], -1};
        }

        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int required = 0;
        int current = 0;

        for (int i = 0; i < 2 * n; i++) {
            current += arr[i][1];
            required = Math.max(required, current);
        }

        return required;
    }
}
```
