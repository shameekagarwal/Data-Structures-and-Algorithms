# Points

- https://leetcode.com/problems/intersection-of-two-arrays/
- naive - two sets so that if for e.g. 2 is contained twice in bth sets, output should only have one 2
- solution 2 - best - one set is enough - when we see an element in the first element, remove it from the first set so that there are no duplicates in the result
- solution 3 - (conditional) - question said all elements are < 1000. so we can use an array as a hash map, which might perform better than java hash map. notice how i use set to 2 then 1 and finally 0, to prevent over counting of elements

# Solution 1

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersection.add(nums2[i]);
            }
        }
        int[] retVal = new int[intersection.size()];
        int i = 0;
        for (int curr : intersection) {
            retVal[i] = curr;
            i +=1;
        }
        return retVal;
    }
}
```

# Solution 2

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                result.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }
        int[] resultArray = new int[result.size()];
        int i = 0;
        for (Integer element : result) {
            resultArray[i] = element;
            i += 1;
        }
        return resultArray;
    }
}
```

# Solution 3

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        short[] map = new short[1001];
        for (int i = 0; i < nums1.length; i++) {
            map[nums1[i]] = 2;
        }
        int intersectionSize = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (map[nums2[i]] == 2) {
                intersectionSize += 1;
                map[nums2[i]] = 1;
            }
        }
        int[] result = new int[intersectionSize];
        int j = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (map[nums2[i]] == 1) {
                result[j] = nums2[i];
                j += 1;
                map[nums2[i]] = 0;
            }
        }
        return result;
    }
}
```
