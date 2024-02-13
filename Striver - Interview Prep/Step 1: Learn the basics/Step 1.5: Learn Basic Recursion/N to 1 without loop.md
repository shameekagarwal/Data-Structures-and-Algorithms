# N to 1 without loop

- https://www.codingninjas.com/studio/problems/n-to-1-without-loop_8357243

```java
public class Solution
{
    public static int[] printNos(int x) {
        int[] result = new int[x];
        fillRecursively(result, 0, x);
        return result;
    }

    private static void fillRecursively(int[] result, int idx, int x) {
        if (idx == x) return;
        result[idx] = x - idx;
        fillRecursively(result, idx + 1, x);
    }
}
```
