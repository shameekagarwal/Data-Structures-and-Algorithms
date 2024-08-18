# Count of Smaller Numbers After Self

- https://leetcode.com/problems/count-of-smaller-numbers-after-self/

```java
class Solution {

    public List<Integer> countSmaller(int[] nums) {

        int n = nums.length;

        int[][] elements = new int[n][];

        for (int i = 0; i < n; i++) {
            elements[i] = new int[]{nums[i], i};
        }

        Integer[] nextSmallers = new Integer[n];
        Arrays.fill(nextSmallers, 0);

        mergeSort(elements, 0, n - 1, nextSmallers);

        return Arrays.asList(nextSmallers);
    }

    private void mergeSort(int[][] nums, int l, int r, Integer[] nextSmallers) {

        if (l >= r) return;

        int m = (l + r) / 2;

        mergeSort(nums, l, m, nextSmallers);
        mergeSort(nums, m + 1, r, nextSmallers);
        merge(nums, l, m, m + 1, r, nextSmallers);
    }

    private void merge(int[][] nums, int s1, int e1, int s2, int e2, Integer[] nextSmallers) {

        List<int[]> merged = new ArrayList<>();

        int p1 = s1;
        int p2 = s2;

        while (p1 <= e1 && p2 <= e2) {

            if (nums[p1][0] < nums[p2][0]) {
                merged.add(nums[p1]);
                nextSmallers[nums[p1][1]] += (p2 - s2);
                p1 += 1;
            } else if (nums[p1][0] > nums[p2][0]) {
                merged.add(nums[p2]);
                p2 += 1;
            } else {
                
                int element = nums[p1][0];

                while (p1 <= e1 && nums[p1][0] == element) {
                    merged.add(nums[p1]);
                    nextSmallers[nums[p1][1]] += (p2 - s2);
                    p1 += 1;
                }

                while (p2 <= e2 && nums[p2][0] == element) {
                    merged.add(nums[p2]);
                    p2 += 1;
                }
            }
        }

        while (p1 <= e1) {
            merged.add(nums[p1]);
            nextSmallers[nums[p1][1]] += (p2 - s2);
            p1 += 1;
        }

        while (p2 <= e2) {
            merged.add(nums[p2]);
            p2 += 1;
        }

        // System.out.println(Arrays.toString(Arrays.copyOfRange(nextSmallers, s1, e2 + 1)));

        for (int i = s1; i <= e2; i++) {
            nums[i] = merged.get(i - s1);
        }
    }
}
```
