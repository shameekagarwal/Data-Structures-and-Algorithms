# Minimum Number of Operations to Make Word K-Periodic

- https://leetcode.com/contest/weekly-contest-396/problems/minimum-number-of-operations-to-make-word-k-periodic/
- store frequency of hashes of all k length substrings from 0 to k-1, k to 2k-1 and so on
- find the max frequency
- now, we need to convert (n / k - this_max_frequency) substrings

```java
class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MUL = 27;

    public int minimumOperationsToMakeKPeriodic(String word, int k) {

        Map<String, Integer> hashFrequency = new HashMap<>();

        for (int i = 0; i < word.length(); i += k) {
            String substring = word.substring(i, i + k);
            hashFrequency.put(substring, hashFrequency.getOrDefault(substring, 0) + 1);
        }

        int maxFrequency = 0;

        for (Integer frequency : hashFrequency.values()) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        return word.length() / k - maxFrequency;
    }
}
```
