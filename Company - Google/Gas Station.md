# Gas Station

- https://leetcode.com/problems/gas-station/
- we can only go from station x to station x+1 if `gas[x] - cost[x]` is > 0
- imagine sum of some integers a + b + c + d + e + f is >= 0
- now, question says we can treat this as a cycle
  - a + b + c + d + e + f
  - b + c + d + e + f + a
  - c + d + e + f + a + b
  - d + e + f + a + b + c
  - e + f + a + b + c + d
  - f + a + b + c + d + e
- one of these cycles never go below 0
- because imagine b-d goes to (-x)
- this means e to b will have to be >= x right? and i will ask you to start from e
- so, condition for returning -1 -> if sum of all `gas[x] - cost[x]` is < 0
- so, maintain a current total - reset it to 0 and reset start to current index if current total ever goes below 0
- keep maintaining a total as well
- if finally total is below 0, return -1, else return the lat point where we reset start

```java
class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int total = 0;
        int currentTotal = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {

            if (currentTotal < 0) {
                currentTotal = 0;
                start = i;
            }

            total += gas[i] - cost[i];
            currentTotal += gas[i] - cost[i];
        }

        return total < 0 ? -1 : start;
    }
}
```
