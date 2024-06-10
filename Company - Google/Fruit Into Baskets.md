# Fruit Into Baskets

- https://leetcode.com/problems/fruit-into-baskets/

```java
class Solution {

    public int totalFruit(int[] fruits) {

        Map<Integer, Integer> lookup = new HashMap<>();

        int l = 0;
        int result = 0;

        for (int r = 0; r < fruits.length; r++) {

            lookup.put(fruits[r], lookup.getOrDefault(fruits[r], 0) + 1);

            while (lookup.size() == 3) {

                lookup.put(fruits[l], lookup.get(fruits[l]) - 1);

                if (lookup.get(fruits[l]) == 0) {
                    lookup.remove(fruits[l]);
                }

                l += 1;
            }

            // System.out.printf("%d-%d: %s\n", l, r, lookup);

            result = Math.max(result, r - l + 1);
        }

        return result;
    }
}
```