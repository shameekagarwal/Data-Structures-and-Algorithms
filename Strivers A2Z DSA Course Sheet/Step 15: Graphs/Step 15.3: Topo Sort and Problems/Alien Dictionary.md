# Alien Dictionary

- https://www.codingninjas.com/studio/problems/alien-dictionary_630423
- we first find all dependencies by comparing every two consecutive words - and we construct a graph from this
- edge case - input - `aa aaaa aaaaaaa` - adjacent words will not have any difference when comparing. so, i return `!`, and when `!` is returned, i know that dependency does not need to be added / indegree does not need to be modified
- my doubt - why is comparing two consecutive words "enough" i.e. why did we not have to add dependency between 1st and 3rd words
  - my understanding - the common prefix between 1st and 3rd words can never be more than the common prefix between 1st and 2nd word
  - this somehow accounts for us not having to check 1st and 3rd words

```java
import java.util.*;

public class Solution {

    public static String getAlienLanguage(String []dictionary, int k) {

        Map<Character, List<Character>> edges = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for (char character = 'a'; character < 'a' + k; character++) {
            edges.put(character, new ArrayList<>());
            indegree.put(character, 0);
        }
        
        for (int i = 1; i < dictionary.length; i++) {
            char[] dependency = getDependency(dictionary[i - 1], dictionary[i]);
            if (dependency[0] == '!') {
                continue;
            }
            edges.get(dependency[0]).add(dependency[1]);
            indegree.put(dependency[1], indegree.get(dependency[1]) + 1);
        }

        Deque<Character> queue = new ArrayDeque<>();

        for (Map.Entry<Character, Integer> element : indegree.entrySet()) {
            if (element.getValue() == 0) {
                queue.addLast(element.getKey());
            }
        }

        StringBuilder characters = new StringBuilder();

        while (!queue.isEmpty()) {

            char node = queue.removeFirst();
            characters.append(node);

            for (char neighbor : edges.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.addLast(neighbor);
                }
            }
        }

        return characters.toString();
    }

    private static char[] getDependency(String a, String b) {
        
        char[] aCharacters = a.toCharArray();
        char[] bCharacters = b.toCharArray();
        
        for (int i = 0; i < Math.min(aCharacters.length, bCharacters.length); i++) {
            if (aCharacters[i] != bCharacters[i]) {
                return new char[]{aCharacters[i], bCharacters[i]};
            }
        }

        return new char[]{'!', '!'};
    }
}
```
