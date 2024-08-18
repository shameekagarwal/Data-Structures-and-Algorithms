# Next Closest Time

- https://leetcode.com/problems/next-closest-time/
- after some time of writing, realized that doing this at one shot is very tricky
- instead, i simulated the entire clock
- number of iterations - 24 * 60 i.e. a full day
- parse the initial minutes and hours
- now, start incrementing one by one minute
- reset minutes when it reaches 60, reset hours when it reaches 100
- last - check if all digits are present in the initial time - remember if hours is 1, we need to check for both 1 and 0

```java
class Solution {

    public String nextClosestTime(String time) {

        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));

        for (int i = 0; i < 24 * 60; i++) {

            minutes += 1;

            if (minutes == 60) {
                
                minutes = 0;
                
                hours += 1;

                if (hours == 24) {
                    hours = 0;
                }
            }

            String serializedHours = hours >= 10 ? Integer.toString(hours) : "0" + hours;
            String serializedMinutes = minutes >= 10 ? Integer.toString(minutes) : "0" + minutes;
            String newTime = serializedHours + ":" + serializedMinutes;

            if (containsAll(newTime, time)) {
                return newTime;
            }
        }

        return time;
    }

    private boolean containsAll(String a, String b) {

        Set<Character> set = new HashSet<>();

        for (char c : b.toCharArray()) {
            set.add(c);
        }

        for (char c : a.toCharArray()) {

            if (!set.contains(c)) {
                return false;
            }
        }

        return true;
    }
}
```
