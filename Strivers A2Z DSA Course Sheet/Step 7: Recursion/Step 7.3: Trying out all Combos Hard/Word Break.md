# Word Break

- https://leetcode.com/problems/word-break/
- if sentence(i...word.length) == word, we should try checking if i+word.length onwards can be broken
- repeating problems - sentence = icecreamman, dict = (ice, cream, icecream)
- we try breaking up to icecream using two ways - but if we already know man cannot be constructed, no point retrying
- time complexity - (len of dict * len of word * len of sentence)?

```java
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] memo = new boolean[s.length()];
        boolean[] seen = new boolean[s.length()];

        return wordBreak(memo, seen, s, wordDict, 0);
    }

    public boolean wordBreak(boolean[] memo, boolean[] seen, String sentence, List<String> wordDict, int idx) {

        if (idx == sentence.length()) {
            return true;
        }

        if (seen[idx]) {
            return memo[idx];
        }

        boolean result = false;

        for (String word : wordDict) {

            if (idx + word.length() <= sentence.length()) {

                if (sentence.substring(idx, idx + word.length()).equals(word)) {

                    if (wordBreak(memo, seen, sentence, wordDict, idx + word.length())) {
                        result = true;
                        break;
                    }
                }
            }
        }

        seen[idx] = true;
        memo[idx] = result;

        return result;
    }
}
```

### Iterative Solution - More Intuitive

```java
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;

        for (int end = 0; end < s.length(); end++) {

            for (String word : wordDict) {

                if (word.length() > end + 1) continue;

                int start = end - word.length() + 1;

                if (s.substring(start, end + 1).equals(word)) {
                    if (canBreak[end + 1 - word.length()]) {
                        canBreak[end + 1] = true;
                        break;
                    }
                }
            }
        }

        // System.out.println(Arrays.toString(canBreak));

        return canBreak[s.length()];
    }
}
```
