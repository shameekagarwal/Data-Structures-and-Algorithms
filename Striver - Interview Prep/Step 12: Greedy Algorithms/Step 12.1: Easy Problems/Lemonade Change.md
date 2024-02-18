# Lemonade Change

- https://leetcode.com/problems/lemonade-change/
- if someone pays 5 - increment 5 counter
- if someone pays 10 - increment 10 counter, decrement 5 counter
- if someone pays 20 - try returning a 10 and a 5 if possible, else 3 5s, because 5s can be used by someone else who pays a 10

```java
class Solution {

    public boolean lemonadeChange(int[] bills) {

        int fiveCount = 0;
        int tenCount = 0;

        for (int bill : bills) {
            if (bill == 5) {
                fiveCount += 1;
            } else if (bill == 10) {
                if (fiveCount == 0) return false;
                fiveCount -= 1;
                tenCount += 1;
            } else if (bill == 20) {
                if (fiveCount >= 1 && tenCount >= 1) {
                    fiveCount -= 1;
                    tenCount -= 1;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
            // System.out.printf("5s: %d, 10s: %d\n", fiveCount, tenCount);
        }
        return true;
    }
}
```
