# 3-Sum Problem

- https://leetcode.com/problems/3sum/
- conditions - 
  - values inside a triplet can be same, but we cannot choose element at same index twice in a triplet
  - distinct triplets - if we have two -1s, we cannot consider a triplet like [-1, 0, 2] twice - a triplet can only occur once
- brute force - O(n^3 + 3log3) (3log3 for sorting list of 3)
  ```
  for (i = 0 < n) {
    for (j = i + 1 < n) {
      for (k = j + 1 < n)
    }
  }
  ```
- optimal -
  - sort
  - iterate through elements
  - use two pointer technique
- important best practice - do not modify input, we use `copyOfRange`

## Solution where Set dedupes for us

- list equals in java - checks `list1.get(i).equals(list2.get(i))`
- so, we can use set to dedupe, because all triplets would be sorted anyway (we will never have a triplet like [2,-2,0])
- a mistake thought - i was thinking `[a,a,a,b,b,b,c,c]` can be made into `[a,b,c]` to avoid duplicates, but that would not work - triplet can be a,a,b i.e. two elements of the same value
- requires extra space for set / extra complexity for maintaining it

```java
class Solution {
    public List<List<Integer>> threeSum(int[] numsRaw) {

        int[] nums = Arrays.copyOfRange(numsRaw, 0, numsRaw.length);
        Arrays.sort(nums);

        Set<List<Integer>> resultDedupe = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] > 0) {
                    k -= 1;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j += 1;
                } else {
                    resultDedupe.add(List.of(nums[i], nums[j], nums[k]));
                    k -= 1;
                    j += 1;
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> triplet : resultDedupe) {
            result.add(triplet);
        }
        return result;
    }
}
```

## Most Optimal - Solution where we handle deduping

- uses no extra space complexity for set / extra time complexity for maintaining it
- so, true O(n^2)
- notice the change in for loop - i++ is not used, instead it is incremented manually "till the number does not change"

```java
class Solution {
    public List<List<Integer>> threeSum(int[] numsRaw) {
        
        int[] nums = Arrays.copyOfRange(numsRaw, 0, numsRaw.length);
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length;) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] > 0) {
                    int currentK = nums[k];
                    while (j < k && currentK == nums[k]) {
                        k -= 1;
                    }
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    int currentJ = nums[j];
                    while (j < k && currentJ == nums[j]) {
                        j += 1;
                    }
                } else {
                    result.add(List.of(nums[i], nums[j], nums[k]));
                    int currentK = nums[k];
                    while (j < k && currentK == nums[k]) {
                        k -= 1;
                    }
                    int currentJ = nums[j];
                    while (j < k && currentJ == nums[j]) {
                        j += 1;
                    }
                }
            }
            int currentI = nums[i];
            while (i < nums.length && currentI == nums[i]) {
                i += 1;
            }
        }

        return result;
    }
}
```
