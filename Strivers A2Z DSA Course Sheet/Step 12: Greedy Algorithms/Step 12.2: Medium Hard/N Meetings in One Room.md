# N Meetings in One Room

- https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
- the sooner the meeting finishes, the more meetings we can do after this meeting
- O(NlogN) (for sorting)
- this line in question - "Start time of one chosen meeting can't be equal to the end time of the other chosen meeting." is for equal time - new meeting time we process has to have start time after (not after or equal) the last meeting end time

```java
class Solution {

    public static int maxMeetings(int start[], int end[], int n) {

        List<int[]> meetings = new ArrayList<>();
        for (int i = 0; i < end.length; i++) {
            meetings.add(new int[]{start[i], end[i]});
        }

        Collections.sort(meetings, (a, b) -> a[1] - b[1]);
        
        int totalMeetingsPossible = 0;
        int lastMeetingEndTime = 0;
        for (int[] meeting : meetings) {
            // System.out.printf("[%d-%d]\n", meeting[0], meeting[1]);
            if (meeting[0] > lastMeetingEndTime) {
                lastMeetingEndTime = meeting[1];
                totalMeetingsPossible += 1;
            }
        }
        return totalMeetingsPossible;
    }
}
```
