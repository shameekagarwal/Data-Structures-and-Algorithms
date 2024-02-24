# Word Ladder II

- https://www.geeksforgeeks.org/problems/word-ladder-ii/1
- remember the logic [here](./Word%20Ladder.md) - there should not be a case that if a word is used at x, it would be used at a level after x
- so, we get rid of a word after using it at a certain level
- note - a word can be present in the answer multiple times - look a the first example on leetcode - for "hot"
- this is why, we get rid of words from the word lookup after we compute the current level
- multiple paths can use the same word as the next node in the path
- so, we only remove a word after current level has been entirely processed - refer `wordsUsed`
- if path removed from queue has last word as the end word, continue the loop
- if after processing current level, result is not empty, break out of the loop - all paths which were in the queue and had the last word as the end word have been added to the result due to the previous point
- the leetcode version is more difficult in terms of time limits / test cases, so skipping leet code for now. little modification is needed to current algorithm to solve for it, but the idea might be that if we have a word x on level y, there might be multiple paths for it - thus causing for e.g. queue to grow very large. we should instead "reconstruct" the path by just storing the last words

```java
class Solution {

    public ArrayList<ArrayList<String>> findSequences(String beginWord, String endWord, String[] wordList) {

        ArrayList<ArrayList<String>> result = new ArrayList<>();

        Set<String> wordLookup = new HashSet<>(Arrays.asList(wordList));

        Deque<ArrayList<String>> queue = new ArrayDeque<>();
        ArrayList<String> startingPath = new ArrayList<>();
        startingPath.add(beginWord);
        queue.addLast(startingPath);

        while (!queue.isEmpty()) {
            
            int currentLevelSize = queue.size();
            Set<String> wordsUsed = new HashSet<>();

            for (int i = 0; i < currentLevelSize; i++) {

                ArrayList<String> path = queue.removeFirst();
                String currentWord = path.get(path.size() - 1);
                char[] currentWordChars = currentWord.toCharArray();
                
                if (currentWord.equals(endWord)) {
                    result.add(path);
                    continue;
                }

                for (int j = 0; j < currentWordChars.length; j++) {
                    
                    char original = currentWordChars[j];

                    for (char replacement = 'a'; replacement <= 'z'; replacement++) {

                        if (replacement == original) continue;

                        currentWordChars[j] = replacement;
                        String neighbor = new String(currentWordChars);

                        if (wordLookup.contains(neighbor)) {
                            ArrayList<String> newPath = new ArrayList<>(path);
                            newPath.add(neighbor);
                            queue.addLast(newPath);
                            wordsUsed.add(neighbor);
                        }
                    }

                    currentWordChars[j] = original;
                }
            }

            if (!result.isEmpty()) {
                break;
            }

            wordLookup.removeAll(wordsUsed);
        }

        return result;
    }
}
```
