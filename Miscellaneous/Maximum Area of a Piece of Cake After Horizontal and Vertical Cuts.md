# Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts

- https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/

```java
class Solution {

    private static final int MOD = 1_000_000_007;

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        List<Integer> horizontalCutsSorted = new ArrayList<>();
        horizontalCutsSorted.add(0);
        horizontalCutsSorted.add(h);
        
        List<Integer> verticalCutsSorted = new ArrayList<>();
        verticalCutsSorted.add(0);
        verticalCutsSorted.add(w);

        for (int cut : horizontalCuts) {
            horizontalCutsSorted.add(cut);
        }

        for (int cut : verticalCuts) {
            verticalCutsSorted.add(cut);
        }

        Collections.sort(horizontalCutsSorted);
        Collections.sort(verticalCutsSorted);

        int maxHorizontalSegment = 0;
        for (int i = 0; i < horizontalCutsSorted.size() - 1; i++) {
            maxHorizontalSegment = Math.max(maxHorizontalSegment, horizontalCutsSorted.get(i + 1) - horizontalCutsSorted.get(i));
        }

        int maxVerticalSegment = 0;
        for (int i = 0; i < verticalCutsSorted.size() - 1; i++) {
            maxVerticalSegment = Math.max(maxVerticalSegment, verticalCutsSorted.get(i + 1) - verticalCutsSorted.get(i));
        }

        return (int) ((maxHorizontalSegment * 1L * maxVerticalSegment) % MOD);
    }
}
```
