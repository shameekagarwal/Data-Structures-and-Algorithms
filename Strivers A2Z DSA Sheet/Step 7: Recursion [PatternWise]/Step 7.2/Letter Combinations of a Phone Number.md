# Letter Combinations of a Phone Number

- https://leetcode.com/problems/letter-combinations-of-a-phone-number/
- generate all combinations for each digit one by one
- finally, append to the result 

```java
class Solution {

    Map<Character, List<Character>> panel = Map.of(
        '2', List.of('a', 'b', 'c'),
        '3', List.of('d', 'e', 'f'),
        '4', List.of('g', 'h', 'i'),
        '5', List.of('j', 'k', 'l'),
        '6', List.of('m', 'n', 'o'),
        '7', List.of('p', 'q', 'r', 's'),
        '8', List.of('t', 'u', 'v'),
        '9', List.of('w', 'x', 'y', 'z')
    );
    
    public List<String> letterCombinations(String digits) {
        
        if (digits.isEmpty()) return List.of();
        
        List<String> result = new ArrayList<>();
        generate(result, digits.toCharArray(), 0, new StringBuilder());
        return result;
    }

    private void generate(List<String> result, char[] digits, int idx, StringBuilder sb) {
        if (idx == digits.length) {
            result.add(sb.toString());
            return;
        }
        for (char c : panel.get(digits[idx])) {
            sb.append(c);
            generate(result, digits, idx + 1, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
}
```
