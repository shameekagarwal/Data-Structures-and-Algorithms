# Text Justification

- https://leetcode.com/problems/text-justification/

```java
class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {

        Map<Integer, List<String>> wordsLookup = new HashMap<>();
        Map<Integer, Integer> spaceLookup = new HashMap<>();

        int lines = populateWordsAndSpacesLookup(words, maxWidth, wordsLookup, spaceLookup);

        List<String> result = new ArrayList<>();

        for (int line = 0; line < lines; line++) {

            StringBuilder currentLine = new StringBuilder();
            int totalWords = wordsLookup.get(line).size();

            if (line == lines - 1 || totalWords == 1) {

                for (int i = 0; i < totalWords; i++) {

                    currentLine.append(wordsLookup.get(line).get(i));

                    if (i != totalWords - 1) {
                        currentLine.append(' ');
                    }
                }

                int carryOverSpaces = spaceLookup.get(line) - (totalWords - 1);

                for (int i = 0; i < carryOverSpaces; i++) {
                    currentLine.append(' ');
                }

            } else {

                int spacesInBetween = spaceLookup.get(line) / (totalWords - 1);
                int carryOverSpaces = spaceLookup.get(line) - (spacesInBetween * (totalWords - 1));

                for (int i = 0; i < totalWords; i++) {

                    currentLine.append(wordsLookup.get(line).get(i));

                    if (i != totalWords - 1) {

                        for (int j = 0; j < spacesInBetween; j++) {
                            currentLine.append(' ');
                        }

                        if (carryOverSpaces > 0) {
                            currentLine.append(' ');
                            carryOverSpaces -= 1;
                        }
                    }
                }
            }

            result.add(currentLine.toString());
        }

        return result;
    }

    private int populateWordsAndSpacesLookup(String[] words, int maxWidth, 
        Map<Integer, List<String>> wordsLookup, Map<Integer, Integer> spaceLookup) {

        int i = 0;
        int line = 0;

        while (i < words.length) {

            int j = 0;
            int lettersOccupied = 0;

            wordsLookup.put(line, new ArrayList<>());

            while (i < words.length && j + words[i].length() <= maxWidth) {

                wordsLookup.get(line).add(words[i]);
                lettersOccupied += words[i].length();

                j += words[i].length();
                j += 1;

                i += 1;
            }

            spaceLookup.put(line, maxWidth - lettersOccupied);

            line += 1;
        }

        return line;
    }
}
```
