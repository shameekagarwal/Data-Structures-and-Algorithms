# Zigzag Conversion

- https://leetcode.com/problems/zigzag-conversion/
- boundary case - return string as is if numRows = 1
- append 1 character for row 0
- append 1 character for rows 1 to numRows - 2
- append 1 character for row numRows - 1
- append 1 character for rows numRows - 2 to 1

```java
class Solution {

    public String convert(String s, int numRows) {

        if (numRows == 1) return s;
        
        char[] arr = s.toCharArray();

        int i = 0;

        Map<Integer, StringBuilder> rowLookup = new HashMap<>();

        while (i < arr.length) {

            int row = 0;

            appendToLookup(rowLookup, row, arr[i]);
            i += 1;
            row += 1;

            while (i < arr.length && row < numRows - 1) {
                appendToLookup(rowLookup, row, arr[i]);
                i += 1;
                row += 1;
            }

            if (i < arr.length) {
                appendToLookup(rowLookup, row, arr[i]);
                i += 1;
                row -= 1;
            }

            while (i < arr.length && row > 0) {
                appendToLookup(rowLookup, row, arr[i]);
                i += 1;
                row -= 1;
            }
        }

        StringBuilder result = new StringBuilder();

        // System.out.println(rowLookup);

        for (int row = 0; row < numRows; row++) {
            result.append(rowLookup.getOrDefault(row, new StringBuilder()));
        }

        return result.toString();
    }

    private void appendToLookup(Map<Integer, StringBuilder> rowLookup, int row, char c) {

        if (!rowLookup.containsKey(row)) {
            rowLookup.put(row, new StringBuilder());
        }

        rowLookup.get(row).append(c);
    }
}
```
