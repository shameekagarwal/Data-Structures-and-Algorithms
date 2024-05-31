# Verbal Arithmetic Puzzle

- https://leetcode.com/problems/verbal-arithmetic-puzzle/
- this is a backtracking question
- try to map all characters to a number
- do not try to map a character to 0 if it is at the beginning of a word / result
- note - for the above, the word / result length should be greater than 1, 0 by itself is allowed, just that leading 0s are not allowed
- tc - 10!? - any digit can be assigned to pretty much any character
- number to character - helps keep track of which numbers have already been used
- character to number - helps construct the final numbers in the base case of the recursion

```java
class Solution {

    public boolean isSolvable(String[] words, String result) {

        Set<Character> characters = new HashSet<>();
        Set<Character> firstCharacters = new HashSet<>();

        for (String word : words) {

            if (word.length() > 1) {
                firstCharacters.add(word.charAt(0));
            }
            
            for (char c : word.toCharArray()) {
                characters.add(c);
            }
        }

        if (result.length() > 1) {
            firstCharacters.add(result.charAt(0));
        }

        for (char c : result.toCharArray()) {
            characters.add(c);
        }

        List<Character> characterList = new ArrayList<>();
        characterList.addAll(characters);

        Integer[] characterToNumber = new Integer[256];
        Character[] numberToCharacter = new Character[10];

        return recurse(0, characterList, firstCharacters, characterToNumber, numberToCharacter, words, result);
    }

    private boolean recurse(int idx, List<Character> characterList, Set<Character> firstCharacters, 
            Integer[] characterToNumber, Character[] numberToCharacter, String[] words, String result) {

        if (idx == characterList.size()) {
            
            int actualResult = 0;

            for (String word : words) {

                int number = 0;

                for (char c : word.toCharArray()) {
                    number = (number * 10) + characterToNumber[c];
                }

                actualResult += number;
            }

            int expectedResult = 0;

            for (char c : result.toCharArray()) {
                expectedResult = (expectedResult * 10) + characterToNumber[c];
            }

            return expectedResult == actualResult;
        }

        char c = characterList.get(idx);
        int start = firstCharacters.contains(c) ? 1 : 0;

        for (int i = start; i <= 9; i++) {

            if (numberToCharacter[i] == null) {
                
                numberToCharacter[i] = c;
                characterToNumber[c] = i;

                if (recurse(idx + 1, characterList, firstCharacters, characterToNumber, numberToCharacter, words, result)) {
                    return true;
                }

                numberToCharacter[i] = null;
                characterToNumber[c] = null;
            }
        }

        return false;
    }
}
```
