# Longest Bitonic Sequence

- https://www.codingninjas.com/studio/problems/longest-bitonic-sequence_1062688
- forward and backward [Longest Increasing Subsequence](./Longest%20Increasing%20Subsequence.md)

```java
import java.util.Arrays;

public class Solution {

    public static int longestBitonicSequence(int[] arr, int n) {

        int[] forward  = new int[n];
        Arrays.fill(forward, 1);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    forward[i] = Math.max(forward[i], forward[j] + 1);
                }
            }
        }

        int[] backward  = new int[n];
        Arrays.fill(backward, 1);

        for (int i = n - 1; i > -1; i--) {

            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    backward[i] = Math.max(backward[i], backward[j] + 1);
                }
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            result = Math.max(result, forward[i] + backward[i] - 1);
        }

        return result;
    }
}
```
