# Two Sum

- https://leetcode.com/problems/two-sum/

```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

## Follow Up

- solve without using a map
- approach - two pointer
- we had to use a custom data structure since on sorting, the index of the element is lost
- my understanding of why this approach can be used - kind of like greedy? say we are at say 0,n-1
  - if `arr[0] + arr[n-1] > k` - we can be sure that left is the smallest possible - so, we have to decrease right
  - similarly, if < k, we can be sure that right is the biggest possible given left - so, we have to increase left
- my doubt, TODO - this feels like a variation of the usual two pointer that i do, where both start from beginning. how to identify from the problem which variation to use

```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new Pair(nums[i], i));
        }
        Collections.sort(list);
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int currentTotal = list.get(left).value + list.get(right).value;
            if (currentTotal > target) right -= 1;
            else if (currentTotal < target) left += 1;
            else return new int[]{list.get(left).idx, list.get(right).idx};
        }
        return new int[]{};
    }
}

class Pair implements Comparable {

    int value, idx;

    Pair(int value, int idx) {
        this.value = value;
        this.idx = idx;
    }

    @Override
    public int compareTo(Object pair) {
        return this.value - ((Pair) pair).value;
    }

    @Override
    public String toString() {
        return "(" + value + ", " + idx + ")";
    }
}
```
