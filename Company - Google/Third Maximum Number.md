# Third Maximum Number

- https://leetcode.com/problems/third-maximum-number/

## Approach 1 - Pointers

```java
class Solution {

    public int thirdMax(int[] nums) {

        Integer gt1 = null;
        Integer gt2 = null;
        Integer gt3 = null;

        for (int i = 0; i < nums.length; i++) {

            if (gt1 == null || nums[i] > gt1) {
                gt3 = gt2;
                gt2 = gt1;
                gt1 = nums[i];
            } else if ((gt2 == null || nums[i] > gt2) && gt1 > nums[i]) {
                gt3 = gt2;
                gt2 = nums[i];
            } else if ((gt3 == null || nums[i] > gt3) && gt2 != null && gt2 > nums[i]) {
                gt3 = nums[i];
            }

            // System.out.printf("%d %d %d\n", gt1, gt2, gt3);
        }

        if (gt3 != null) return gt3;

        return gt1;
    }
}
```

## Approach 2 - Set

- use a "tree set" to maintain ordering
- if set size goes above 3 - remove th first element from it
- if set size is 1 - return first element
- if set size is 2 - return second element
- else, return first element

```java
class Solution {

    public int thirdMax(int[] nums) {

        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {

            set.add(nums[i]);

            if (set.size() > 3) {
                set.remove(set.iterator().next());
            }
        }

        Iterator<Integer> iterator = set.iterator();

        if (set.size() == 1 || set.size() == 3) {
            return iterator.next();
        } else {
            iterator.next();
            return iterator.next();
        }
    }
}
```
