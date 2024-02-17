# Count Number of Nice Subarrays

- https://leetcode.com/problems/count-number-of-nice-subarrays/
- logic is exactly same as [Binary Subarrays With Sum](./Binary%20Subarrays%20With%20Sum.md), so explanation is same, just writing both code snippets

```java
class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        
        int l = 0;
        int numberOfOddsSeen = 0;
        Map<Integer, Integer> oddFrequency = new HashMap<>();
        oddFrequency.put(0, 1);
        int result = 0;

        for (int r = 0; r < nums.length; r++) {
            if (nums[r] % 2 != 0) {
                numberOfOddsSeen += 1;
            }
            result += oddFrequency.getOrDefault(numberOfOddsSeen - k, 0);
            oddFrequency.put(numberOfOddsSeen, oddFrequency.getOrDefault(numberOfOddsSeen, 0) + 1);
            // System.out.println(oddFrequency);
        }

        return result;
    }
}
```

## Optimal

```java
class Solution {

    public int numberOfSubarrays(int[] nums, int k) {

        return numberOfSubarraysAtMost(nums, k) - numberOfSubarraysAtMost(nums, k - 1);
    }

    private int numberOfSubarraysAtMost(int[] nums, int k) {
        
        int l = 0;
        int numberOfOddsInCurrentWindow = 0;
        int result = 0;
        
        for (int r = 0; r < nums.length; r++) {

            if (nums[r] % 2 != 0) {
                numberOfOddsInCurrentWindow += 1;
            }

            while (l <= r && numberOfOddsInCurrentWindow > k) {
                if (nums[l] % 2 != 0) {
                    numberOfOddsInCurrentWindow -= 1;
                }
                l += 1;
            }

            result += (r - l);
        }

        return result;
    }
}
```
