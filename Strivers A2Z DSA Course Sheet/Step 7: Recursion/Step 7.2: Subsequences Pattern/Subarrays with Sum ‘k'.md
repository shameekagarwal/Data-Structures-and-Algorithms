# Subarrays with Sum ‘k'

- https://www.codingninjas.com/studio/problems/subarrays-with-sum-%E2%80%98k'_6922076
- finding subarrays with sum k - put to a map, if we find numbers with currentSum - k, we can add them to the result
- i find all subarray bounds like this and finally, append them to the result
- note - i had to use `intArrayToIntegerResult` because `List<Integer> nums = Arrays.asList(int[] a)` does not work, maybe because autoboxing does not work if we are going array to list and only works when it is by itself? the error might not be very readable in online coding assessments
- i think this approach worked since all were positive integers, otherwise there could be multiple sub arrays ending at i with sum k - in which case my Map would be `List<Long, List<Integer>>` i.e. contain a list of indices for a particular prefix sum

```java
import java.util.*;

public class Solution {
    
    public static List<List<Integer>> subarraysWithSumK(int []nums, long k) {

        Map<Long, Integer> map = new HashMap<>();
        long currentSum = 0;
        map.put(0L, 0);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (map.containsKey(currentSum - k)) {
                int[] subarray = Arrays.copyOfRange(nums, map.get(currentSum - k), i + 1);
                result.add(intArrayToIntegerList(subarray));
            }
            map.put(currentSum, i + 1);
        }

        return result;
    }

    private static List<Integer> intArrayToIntegerList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        return list;
    }
}
```

- note - based on [Subarray Sum Equals K](../../Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.2:%20Medium/Subarray%20Sum%20Equals%20K.md), we can get rid of the map by using two pointer like approach, since question says numbers are positive

```java
import java.util.*;

public class Solution {
    
    public static List<List<Integer>> subarraysWithSumK(int []nums, long k) {

        long currentSum = 0;
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;

        for (int i = 0; i < nums.length; i++) {

            currentSum += nums[i];
            while (left < i && currentSum > k) {
                currentSum -= nums[left];
                left += 1;
            }

            if (currentSum == k) {
                int[] subarray = Arrays.copyOfRange(nums, left, i + 1);
                result.add(intArrayToIntegerList(subarray));
            }
        }

        return result;
    }

    private static List<Integer> intArrayToIntegerList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        return list;
    }
}
```

- note - this question is in recursion! i could not find a good way to generate subarrays using recursion. using recursion for subarrays felt like a stretch. one way can be - 
  ```java
  recurse(nums, 0)

  // ...

  void recurse(int[] nums, int start) {
    if (start == nums.length) return;
    for (int i = start; i < nums.length; i++) {
      // start-i is a subarray
    }
    recurse(nums, start + 1);
  }
  ```
