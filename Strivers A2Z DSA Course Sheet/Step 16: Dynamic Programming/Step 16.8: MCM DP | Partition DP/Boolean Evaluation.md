# Boolean Evaluation

- https://www.codingninjas.com/studio/problems/boolean-evaluation_1214650

```java
public class Solution {

    private static final int MOD = 1000000007;

    public static int evaluateExp(String exp) {

        char[] expr = exp.toCharArray();
        int n = expr.length;

        Pair[][] memo = new Pair[n][n];
        boolean[][] seen = new boolean[n][n];

        return recurse(0, n - 1, expr, memo, seen).truthy;
    }

    private static Pair recurse(int start, int end, char[] expr, Pair[][] memo, boolean[][] seen) {

        if (seen[start][end]) {
            return memo[start][end];
        }

        Pair total = new Pair(0, 0);

        if (start == end) {
            if (expr[start] == 'T') {
                total.truthy = 1;
            } else if (expr[start] == 'F') {
                total.falsy = 1;
            }
        }

        for (int partition = start; partition <= end; partition++) {
    
            if (expr[partition] == 'T' || expr[partition] == 'F') continue;

            Pair left = recurse(start, partition - 1, expr, memo, seen);
            Pair right = recurse(partition + 1, end, expr, memo, seen);

            // System.out.printf("%d: %s, %d, %d: %s\n", start, left, partition, end, right);

            if (expr[partition] == '&') {
                total.falsy = add(total.falsy, mul(left.falsy, right.falsy), mul(left.truthy, right.falsy), mul(left.falsy, right.truthy));
                total.truthy = add(total.truthy, mul(left.truthy, right.truthy));
            } else if (expr[partition] == '|') {
                total.falsy = add(total.falsy, mul(left.falsy, right.falsy));
                total.truthy = add(total.truthy, mul(left.truthy, right.truthy), mul(left.truthy, right.falsy), mul(left.falsy, right.truthy));
            } else if (expr[partition] == '^') {
                total.falsy = add(total.falsy, mul(left.truthy, right.truthy), mul(left.falsy, right.falsy));
                total.truthy = add(total.truthy, mul(left.truthy, right.falsy), mul(left.falsy, right.truthy));
            }
        }

        // System.out.printf("%d, %d: %s\n", start, end, total);
        seen[start][end] = true;
        memo[start][end] = total;

        return total;
    }

    private static int mul(int... a) {
        long result = 1;
        for (int i : a) {
            result = (result * i) % MOD;
        }
        return (int) result;
    }

     private static int add(int... a) {
        long result = 0;
        for (int i : a) {
            result = (result + i) % MOD;
        }
        return (int) result;
    }

    static class Pair {

        int truthy;
        int falsy;

        Pair(int truthy, int falsy) {
            this.truthy = truthy;
            this.falsy = falsy;
        }

        @Override
        public String toString() {
            return "(" + truthy + ", " + falsy + ")";
        }
    }
}
```
