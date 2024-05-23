# Longest Absolute File Path

- https://leetcode.com/problems/longest-absolute-file-path/
- there can be multiple folders at a level right
- observation 1 - but if we process in order, we only need to care about "the last folder / file that we saw for a level"
- any file / folder at level x+1 will be a child of last seen folder at level x
- observation 2 - for every level, we only need to maintain the "cumulative sum"
- for level x+1, result will be result of level x + contribution by adding current file / folder
- how to calculate cumulative sum - 
  - if level is 0 - length is total length of directory / file
  - note - level 0 need not always be dir, so above calculation is useful for use cases like -
    ```
    file1.txt
    file2.txt
    newFile3.txt
    ```
  - else, length is sum of - 
    - length of last seen of previous level
    - current directory / file's length
    - +1 for the `/` character

```java
class Solution {

    public int lengthLongestPath(String input) {

        String[] lines = input.split("\n");

        Map<Integer, Integer> levelLookup = new HashMap<>();

        int result = 0;

        for (int i = 0; i < lines.length; i++) {

            int level = 0;

            while (level < lines[i].length() && lines[i].charAt(level) == '\t') {
                level += 1;
            }

            int totalLength;

            if (level == 0) {
                totalLength = lines[i].length();
            } else {
                totalLength = levelLookup.get(level - 1) + lines[i].length() - level + 1;
            }

            levelLookup.put(level, totalLength);

            if (lines[i].contains(".")) {
                result = Math.max(result, levelLookup.get(level));   
            }
        }

        return result;
    }
}
```
