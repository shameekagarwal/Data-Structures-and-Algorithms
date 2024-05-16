# Minimum Length of Anagram Concatenation

- https://leetcode.com/contest/weekly-contest-396/problems/minimum-length-of-anagram-concatenation/
- a number can have sqrt(N) factors, so srt(N) complexity
- so, for each of this factors, we try checking if this window length is possible
- checking takes 26 * N complexity
- so, overall complexity is `N * sqrt(N) * 26` i guess

```java
class Solution {

    public int minAnagramLength(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        for (int window = 1; window <= n; window++) {

            if (n % window != 0) continue;

            Map<Character, Integer> frequencyLookup = 
                calculateFrequencyLookup(arr, 0, window);

            boolean isPossible = true;

            for (int i = window; i < n; i += window) {

                Map<Character, Integer> currentFrequencyLookup = 
                    calculateFrequencyLookup(arr, i, window);

                if (!currentFrequencyLookup.equals(frequencyLookup)) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                return window;
            }
        }

        return n;
    }

    private Map<Character, Integer> calculateFrequencyLookup(char[] arr, int start, int length) {

        Map<Character, Integer> frequencyLookup = new HashMap<>();

        for (int i = start; i < start + length; i++) {
            frequencyLookup.put(arr[i], frequencyLookup.getOrDefault(arr[i], 0) + 1);
        }

        return frequencyLookup;
    }
}
```
