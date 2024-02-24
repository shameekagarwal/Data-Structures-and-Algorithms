# Word Ladder

- https://leetcode.com/problems/word-ladder/
- all lengths are same
- for each word, perform the possible transition and see if word list contains it. if yes, move to that node
- track word list via unordered set for faster access
- another observation - e.g. assume a word was encountered at the 3rd level. we remove it from the set. could it not have happened that the best path was by using the word in the 5th level?
- no - we are computing all possibilities. a path from this word occurring at 3rd level will always be lesser than a path from this word occurring at 5th level, because path from this word to destination does not change
- imp - notice the use of char array to make using strings simpler as compared to using `.substring` etc
- time complexity - O(N * word length * 26) - for each word, we look at word length * 26 possibilities
- my doubt - an extra word length factor should be multiplied to the time complexity because we compute hash code for strings to check if string is present in set, we perform .equals, etc

```java
class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordsLookup = new HashSet<>();
        wordsLookup.addAll(wordList);

        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(beginWord);

        int currentPathLength = 1;

        while (!queue.isEmpty()) {
            
            int currentLevelSize = queue.size();
            currentPathLength += 1;
            
            for (int i = 0; i < currentLevelSize; i++) {
                
                char[] wordArr = queue.removeFirst().toCharArray();
                
                for (int j = 0; j < wordArr.length; j++) {
                    
                    char original = wordArr[j];
                    
                    for (int k = 0; k < 'z' - 'a' + 1; k++) {
                        
                        char replacement = (char) ('a' + k);
                        if (replacement == original) continue;
                        wordArr[j] = replacement;
                        String neighboringWord = new String(wordArr);
                        
                        if (wordsLookup.contains(neighboringWord)) {

                            if (neighboringWord.equals(endWord)) {
                                return currentPathLength;
                            }

                            wordsLookup.remove(neighboringWord);
                            queue.addLast(neighboringWord);
                        }
                    }

                    wordArr[j] = original;
                }
            }

            // System.out.println(queue);
        }

        return 0;
    }
}
```
