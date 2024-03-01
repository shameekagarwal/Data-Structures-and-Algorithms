# Subset Sum Equal To K

- https://www.codingninjas.com/studio/problems/subset-sum-equal-to-k_1550954
- 2 possibilities for an index - either it is a part, or it is not
- time complexity - O(2^n)

```java
import java.util.*;
import java.io.*;

public class Solution {

    public static boolean subsetSumToK(int n, int k, int arr[]) {
        return subsetSumToK(0, n, k, arr);
    }

    private static boolean subsetSumToK(int idx, int n, int k, int arr[]) {

        if (k == 0) return true;

        if (idx == n) return false;

        boolean answer = subsetSumToK(idx + 1, n, k, arr) || subsetSumToK(idx + 1, n, k - arr[idx], arr);

        return answer;
    }
}
```

- how to apply memoization - see what are the changing parameters - index and target in this case
- now, apply memoization on that
- note - i am using map and not array, to save on space
- time complexity - O(N*target)

```java
import java.util.*;
import java.io.*;

public class Solution {

    public static boolean subsetSumToK(int n, int k, int arr[]) {
        Map<Integer, Map<Integer, Boolean>> dp = new HashMap<>();
        return subsetSumToK(0, n, k, arr, dp);
    }

    private static boolean subsetSumToK(int idx, int n, int k, int arr[], Map<Integer, Map<Integer, Boolean>> dp) {

        if (k == 0) return true;

        if (idx == n) return false;

        if (dp.containsKey(idx) && dp.get(idx).containsKey(k)) {
            return dp.get(idx).get(k);
        }

        boolean answer = subsetSumToK(idx + 1, n, k, arr, dp) || subsetSumToK(idx + 1, n, k - arr[idx], arr, dp);

        if (!dp.containsKey(idx)) {
            dp.put(idx, new HashMap<>());
        }

        dp.get(idx).put(k, answer);

        return answer;
    }
}
```

- tabular 

```java
import java.util.*;
import java.io.*;

public class Solution {

    public static boolean subsetSumToK(int n, int k, int arr[]) {

        Map<Integer, Set<Integer>> dp = new HashMap<>();
        dp.put(-1, new HashSet<>());
        dp.get(-1).add(0);

        for (int i = 0; i < n; i++) {

            dp.put(i, new HashSet<>());

            for (int j = 0; j <= k; j++) {
                if (dp.get(i - 1).contains(j) || dp.get(i - 1).contains(j - arr[i])) {
                    dp.get(i).add(j);
                }
            }
        }

        return dp.get(n - 1).contains(k);
    }
}
```

- space optimized + tabular

 ```java
import java.util.*;
import java.io.*;

public class Solution {

    public static boolean subsetSumToK(int n, int k, int arr[]) {

        Set<Integer> previous = new HashSet<>();
        previous.add(0);

        for (int i = 0; i < n; i++) {

            Set<Integer> current = new HashSet<>();

            for (int j = 0; j <= k; j++) {
                if (previous.contains(j) || previous.contains(j - arr[i])) {
                    current.add(j);
                }
            }

            previous = current;
        }

        return previous.contains(k);
    }
}
```
