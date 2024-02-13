# Print n times

- https://www.codingninjas.com/studio/problems/-print-n-times_8380707

```java
import java.util.*;

public class Solution {
    public static List<String> printNtimes(int n){
        List<String> result = new ArrayList<>();
        recursivelyFill(result, n);
        return result;
    }

    private static void recursivelyFill(List<String> result, int n) {
        if (n == 0) return;
        result.add("Coding Ninjas");
        recursivelyFill(result, n - 1);
    }
}
```
