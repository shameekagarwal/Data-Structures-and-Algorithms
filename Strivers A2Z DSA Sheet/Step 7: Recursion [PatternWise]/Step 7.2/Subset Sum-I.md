# Subset Sum-I

- https://www.codingninjas.com/studio/problems/subset-sum_3843086
- generate all subsequences - pick or do not pick an element
- sort results at end
- time complexity - `2^n + (2^n * log(2^n))` - (generating + sorting)

```java
import java.util.* ;
import java.io.*;

public class Solution {
    
    public static ArrayList<Integer> subsetSum(int num[]) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        recurse(result, num, 0, 0);
        Collections.sort(result);
        return result;
    }

    private static void recurse(ArrayList<Integer> result, int[] num, int currentSum, int idx) {
        if (idx == num.length) {
            result.add(currentSum);
            return;
        }
        recurse(result, num, currentSum + num[idx], idx + 1);
        recurse(result, num, currentSum, idx + 1);
    }
}
```
