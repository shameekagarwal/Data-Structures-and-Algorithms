# Delete Operation for Two Strings

- https://leetcode.com/problems/delete-operation-for-two-strings/
- what can we "not touch"? - anything that is already matching - longest common subsequence
- now, remove everything else
- so, we just need to delete everything that is not a part of the lcs
- so, n1 + n2 - 2*lcs
- note - the answer would be the same if the question permitted insertions as an operation as well - either we can insert everything remaining / remove everything extra / do a combination of both. length of lcs will change accordingly, but number of operations stays the same

```java
class Solution {

    public int minDistance(String word1, String word2) {

        char[] word1Arr = word1.toCharArray();
        char[] word2Arr = word2.toCharArray();

        int m = word1Arr.length;
        int n = word2Arr.length;

        int[] previous = new int[n];

        for (int i = 0; i < m; i++) {

            int[] current = new int[n];

            for (int j = 0; j < n; j++) {
                if (word1Arr[i] == word2Arr[j]) {
                    current[j] = (j == 0 ? 0 : previous[j - 1]) + 1;
                } else {
                    current[j] = Math.max(
                        j == 0 ? 0 : current[j - 1],
                        i == 0 ? 0 : previous[j]
                    );
                }
            }

            previous = current;
        }

        return m + n - (2 * previous[n - 1]);
    }
}
```
