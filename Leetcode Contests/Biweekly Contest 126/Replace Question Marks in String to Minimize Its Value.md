# Replace Question Marks in String to Minimize Its Value

- https://leetcode.com/problems/replace-question-marks-in-string-to-minimize-its-value/
- it always makes sense to place the character with the least frequency
- if multiple characters have the same frequency, it makes sense to place the smallest character
- we use priority queue for this
- now, order of characters does not matter for cost
- so, after finding what characters we can use for our string, we sort them before placing it on the question marks

```java
class Solution {
    
    private static final int NO_OF_LOWER = 'z' - 'a' + 1;

    public String minimizeStringValue(String s) {

        char[] characters = s.toCharArray();

        int[] frequency = new int[NO_OF_LOWER];
        for (char c : characters) {
            if (c != '?') {
                frequency[c - 'a'] += 1;
            }
        }

        PriorityQueue<PQNode> pq = new PriorityQueue<>();
        
        for (int i = 0; i < NO_OF_LOWER; i++) {
            pq.add(new PQNode((char) (i + 'a'), frequency[i]));
        }
        
        List<Character> charactersToAdd = new ArrayList<>();
        int charactersToAddPtr = 0;
        
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '?') {
                PQNode pqNode = pq.remove();
                charactersToAdd.add(pqNode.character);
                // characters[i] = pqNode.character;
                pqNode.frequency += 1;
                pq.add(pqNode);
            }
        }

        Collections.sort(charactersToAdd);
               
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '?') {
                characters[i] = charactersToAdd.get(charactersToAddPtr);
                charactersToAddPtr += 1;
            }
        }

        return new String(characters);
    }
    
    static class PQNode implements Comparable<PQNode> {
        
        char character;
        int frequency;

        PQNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(PQNode node) {
            if (frequency == node.frequency) {
                return character - node.character;
            }
            return frequency - node.frequency;
        }
        
        @Override
        public String toString() {
            return "(" + character + ": " + frequency + ")";
        }
    }
}
```
