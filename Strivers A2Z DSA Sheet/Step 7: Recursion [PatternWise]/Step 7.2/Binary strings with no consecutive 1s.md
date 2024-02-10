# Binary strings with no consecutive 1s

- https://www.codingninjas.com/studio/problems/-binary-strings-with-no-consecutive-1s._893001
- add 0 for current idx
- add 1 for current idx if
  - string is empty
  - or last element was not 1

```java
import java.util.List;
import java.util.ArrayList;

public class Solution {
    
    public static List<String> generateString(int N) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[N];
        generateStringRec(result, chars, 0);
        return result;
    }

    private static void generateStringRec(List<String> result, char[] chars, int idx) {
        if (idx == chars.length) {
            result.add(new String(chars));
            return;
        }
        chars[idx] = '0';
        generateStringRec(result, chars, idx + 1);
        if (idx == 0 || chars[idx - 1] != '1') {
            chars[idx] = '1';
            generateStringRec(result, chars, idx + 1);
        }
    }
}
```
