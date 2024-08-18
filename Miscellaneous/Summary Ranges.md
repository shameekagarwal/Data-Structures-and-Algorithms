# Summary Ranges

- https://leetcode.com/problems/summary-ranges/

## Approach 1 - With Stack

- use a stack
- add 0,0 to stack start iterating from 1
- two cases -
  - either update end of previous based on range
  - or add a new element i,i
- space - O(n) for stack, O(n) for result

```java
class Solution {

    public List<String> summaryRanges(int[] nums) {

        if (nums.length == 0) {
            return List.of();
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{nums[0], nums[0]});

        for (int i = 1; i < nums.length; i++) {
            if (deque.peekLast()[1] == nums[i] - 1) {
                deque.peekLast()[1] = nums[i];
            } else {
                deque.addLast(new int[]{nums[i], nums[i]});
            }
        }

        List<String> result = new ArrayList<>();

        while (!deque.isEmpty()) {

            int[] range = deque.removeFirst();

            if (range[0] == range[1]) {
                result.add(Integer.toString(range[0]));
            } else {
                result.add(range[0] + "->" + range[1]);
            }
        }

        return result;
    }
}
```

## Approach 2 - Without Stack

- if interviewer cries about stack extra space

```java
class Solution {

    public List<String> summaryRanges(int[] nums) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < nums.length;) {

            int j = i + 1;

            while (j < nums.length && nums[j] == nums[j - 1] + 1) {
                j += 1;
            }

            j -= 1;

            if (i == j) {
                result.add(Integer.toString(nums[i]));
            } else {
                result.add(nums[i] + "->" + nums[j]);
            }

            i = j + 1;
        }

        return result;
    }
}
```

