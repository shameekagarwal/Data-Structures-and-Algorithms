# Reverse Words in a String

- https://leetcode.com/problems/reverse-words-in-a-string/
- this question is probably about realizing boundary cases without hitting submit
- only add word if current word is not empty - or we will add empty words to list and when joining, we would join empty strings with redundant spaces - "   hello everyone   in world  " should be "world in everyone hello" i.e. no redundant spaces
- print an array to help debug using `Arrays.toString(arr)`
- `Arrays.asList` to convert array to list
- `Collections.reverse` to reverse a list - remember it is in place
- look how we use streams as well along with `Collectors.joining`

```java
class Solution {
    public String reverseWords(String s) {
        
        String[] wordsArr = s.split(" ");
        // System.out.println(Arrays.toString(words));
        List<String> words = Arrays.asList(wordsArr);
        Collections.reverse(words);
        return words
            .stream()
            .filter(word -> !word.isEmpty())
            .collect(Collectors.joining(" "));
    }
}
```
