# Intersection of Two Arrays II

- https://leetcode.com/problems/intersection-of-two-arrays-ii/
- off top not aware of stl that can handle this - we need a "multiset" with inbuilt methods for "intersection"
- if array is sorted - use pointers like merge algorithm, constant memory is possible
- above approach might also be useful when arrays are too big to be loaded inside memory
- another optimization - hash map uska banao jiska size chota ho - aur bade wale ko stream karo for iterating
- note - i made a naive mistake by using two hash maps - 1 hash map is enough for this, 1 hash map would be rejected

```java
class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> nums1Lookup = getLookup(nums1);
        List<Integer> intersection = new ArrayList<>();

        for (int i : nums2) {

            if (nums1Lookup.containsKey(i)) {

                intersection.add(i);

                if (nums1Lookup.get(i) == 1) {
                    nums1Lookup.remove(i);
                } else {
                    nums1Lookup.put(i, nums1Lookup.get(i) - 1);
                }
            }
        }

        int[] intersectionArr = new int[intersection.size()];

        for (int i = 0; i < intersection.size(); i++) {
            intersectionArr[i] = intersection.get(i);
        }

        return intersectionArr;
    }

    private Map<Integer, Integer> getLookup(int[] nums) {

        Map<Integer, Integer> lookup = new HashMap<>();

        for (int i : nums) {
            lookup.put(i, lookup.getOrDefault(i, 0) + 1);
        }

        return lookup;
    }
}
```
