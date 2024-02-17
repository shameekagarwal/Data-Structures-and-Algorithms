# Longest Substring Without Repeating Characters

- https://leetcode.com/problems/longest-substring-without-repeating-characters/
- naive - check all substrings - O(n^2)

## Efficient

- move pointer r by one step
- keep moving bound l by 1 step, till window `[l,r-1]` contains the character at r
- finally, `[l,r]` would contain all characters (including character at r) only once
- time complexity - O(2 * n) - both l and r are moving
- another note - question said characters are ascii, not unicode
- recall from [this](/Striver%20-%20Interview%20Prep/Step%201:%20Learn%20the%20basics/Step%201.6:%20Learn%20Basic%20Hashing/Hashing%20Theory.md)
  - java char can store unicode, so 2 bytes or 2^16 bits or 65536
  - in cpp etc char can store ascii so 1 byte or 2^8 or 256
- based on question, 256 is enough
- else, the constant could also have been `1<<16` etc
- also, note the use of `Arrays.fill` stl

```java
class Solution {

    private static final int TOTAL_NO_OF_CHARS = 256;

    public int lengthOfLongestSubstring(String s) {

        int[] map = new int[TOTAL_NO_OF_CHARS];
        Arrays.fill(map, -1);
        int l = 0;
        char[] characters = s.toCharArray();

        int result = 0;

        for (int r = 0; r < characters.length; r++) {
            // System.out.printf("%c: %d\n", characters[r], map[characters[r]]);
            while (map[characters[r]] != -1) {
                map[characters[l]] = -1;
                l += 1;
            }
            result = Math.max(result, r - l + 1);
            map[characters[r]] = r;
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
  - step 1 - l = 0, r = 0, map = { a: 0, b: -1 }
  - step 2 - l = 0, r = 1, map = { a: 0, b: 1 }
  - step 3 - l = 2, r = 2, map = { a: 0, b: 2 }
  - step 4 - now, `map[a]` is not -1 i.e. it looks like it is occupied
- but, we know that l i.e. left bound of window has "gone ahead" of the index that is stored at a
- so, we have to ignore that as well. so, l is `max(map[char[r]] + 1, l)`

```java
class Solution {

    private static final int TOTAL_NO_OF_CHARS = 256;

    public int lengthOfLongestSubstring(String s) {

        int[] map = new int[TOTAL_NO_OF_CHARS];
        Arrays.fill(map, -1);
        int l = 0;
        char[] characters = s.toCharArray();

        int result = 0;

        for (int r = 0; r < characters.length; r++) {
            if (map[characters[r]] != -1) {
                l = Math.max(map[characters[r]] + 1, l);
            }
            result = Math.max(result, r - l + 1);
            map[characters[r]] = r;
        }

        return result;
    }
}
```
