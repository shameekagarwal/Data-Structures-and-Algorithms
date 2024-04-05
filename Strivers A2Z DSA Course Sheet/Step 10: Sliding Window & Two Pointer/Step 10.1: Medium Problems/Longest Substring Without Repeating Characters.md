# Longest Substring Without Repeating Characters

- https://leetcode.com/problems/longest-substring-without-repeating-characters/
- naive - check all substrings - O(n^2)

## Efficient

- move pointer r by one step
- keep moving bound l by 1 step, till window `[l,r-1]` contains the character at r
- finally, `[l,r]` would contain all characters (including character at r) only once
- time complexity - O(2 * n) - both l and r are moving

```java
class Solution {

    public int lengthOfLongestSubstring(String s) {

        char[] characters = s.toCharArray();
        int n = characters.length;
        Set<Character> unqiueCharacters = new HashSet<>();
        int result = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {

            while (unqiueCharacters.contains(characters[r])) {
                unqiueCharacters.remove(characters[l]);
                l += 1;
            }

            unqiueCharacters.add(characters[r]);

            result = Math.max(r - l + 1, result);
        }

        return result;
    }
}
```

## Optimal

- why jump one by one
- we can directly jump to the index just after the previous occurrence of the character at r
- but note - previously, when we were moving l one by one, we were also marking character at l as -1
- we cannot do that anymore once we jump l in one go
- e.g. abba - 
  - step 1 - l = 0, r = 0, map = { a: 0 }
  - step 2 - l = 0, r = 1, map = { a: 0, b: 1 }
  - step 3 - l = 2, r = 2, map = { a: 0, b: 2 }
  - step 4 - now, `map['a']` has 0
- but, we know that l i.e. left bound of window has "gone ahead" of the index that is stored at a
- so, we have to ignore that as well. so, l is `max(map[char[r]] + 1, l)`

```java
class Solution {

    public int lengthOfLongestSubstring(String s) {

        char[] characters = s.toCharArray();
        int n = characters.length;
        Map<Character, Integer> lookup = new HashMap<>();
        int result = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {

            if (lookup.containsKey(characters[r])) {
                l = Math.max(lookup.get(characters[r]) + 1, l);
            }

            lookup.put(characters[r], r);

            result = Math.max(r - l + 1, result);
        }

        return result;
    }
}
```
