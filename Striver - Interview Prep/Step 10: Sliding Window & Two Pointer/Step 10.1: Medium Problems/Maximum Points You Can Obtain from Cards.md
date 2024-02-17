# Maximum Points You Can Obtain from Cards

- https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
- if you pick a cards from left, you will have to pick k - a cards from right
- first, calculate sum of cards from 0...k-1 and set result to it
- then, keep decreasing elements from first half (by removing value of card at k-1, then k-2 and so on)
- and keep increasing elements from second half (by adding value of card at n-1, then n-2 and so on)

```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += cardPoints[i];
        }

        int result = currentSum;
        int endPtr = cardPoints.length - 1;
        int startPtr = k - 1;
        for (int i = 0; i < k; i++) {
            currentSum -= cardPoints[startPtr];
            currentSum += cardPoints[endPtr];
            endPtr -= 1;
            startPtr -= 1;
            result = Math.max(currentSum, result);
        }
        return result;
    }
}
```
