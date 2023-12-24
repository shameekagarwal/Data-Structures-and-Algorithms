# Points

- https://leetcode.com/problems/group-anagrams/
- solution 1 - key of hashmap can be sorted strings
- solution 2 - make a count map of characters, and use string representation of this count map. e.g. click would become - 
  ```
  a b c....i j k l.....x y z
  0 0 2....1 0 1 1.....0 0 0
  ```
- note - apparently, String.valueOf is more performant than Arrays.toString. wasn't getting good runtime with Arrays.toString, but this shouldn't be relevant for interview

# Solution 1

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            
            char[] characters = s.toCharArray();
            Arrays.sort(characters);
            String sortedS = new String(characters);

            if (!map.containsKey(sortedS)) {
                map.put(sortedS, new ArrayList<>());
            }
            map.get(sortedS).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
```

# Solution 2

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] count = new char[26];
            for (char c : s.toCharArray()) {
                count[c - 'a'] += 1;
            }
            String countS = String.valueOf(count);

            if (!map.containsKey(countS)) {
                map.put(countS, new ArrayList<>());
            }
            map.get(countS).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
```
