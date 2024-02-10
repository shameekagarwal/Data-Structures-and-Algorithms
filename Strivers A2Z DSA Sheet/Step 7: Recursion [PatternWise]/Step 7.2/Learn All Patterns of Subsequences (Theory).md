# Learn All Patterns of Subsequences (Theory)

- given a list and sum k
- three patterns are there - 

## Find all Subsequences with Sum k

- base case - add calculated result to a list of results

```java
public class Solution {

  public static void main(String[] args) {
    new Solution().run(new int[]{1, 2, 1}, 2);
  }

  public void run(int[] nums, int k) {
    List<List<Integer>> result = new ArrayList<>();
    rec(result, 0, k, new boolean[nums.length], nums, 0);
    System.out.println(result);
  }

  private void rec(List<List<Integer>> result, int idx, int k, boolean[] use, int[] nums, int currentSum) {
    if (idx == nums.length) {
      if (currentSum == k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < use.length; i++){
          if (use[i]) list.add(nums[i]);
        }
        result.add(list);
      }
      return;
    }
    if (currentSum + nums[idx] <= k) {
      use[idx] = true;
      rec(result, idx + 1, k, use, nums, currentSum + nums[idx]);
    }
    use[idx] = false;
    rec(result, idx + 1, k, use, nums, currentSum);
  }
}
```

## If a Subsequence with Sum K Exists

- if one of the branches return true, do not evaluate other branches
- base case - evaluate if condition is satisfied

```java
public class Solution {

  public static void main(String[] args) {
    new Solution().run(new int[]{1, 2, 1}, 2);
    new Solution().run(new int[]{1, 2, 1}, 5);
    new Solution().run(new int[]{1, 2, 1}, 3);
    new Solution().run(new int[]{1, 2, 1}, 1);
  }

  public void run(int[] nums, int k) {
    boolean result = rec(0, k, nums, 0);
    System.out.println(k + ": " + result);
  }

  private boolean rec(int idx, int k, int[] nums, int currentSum) {
    if (idx == nums.length) {
      return currentSum == k;
    }
    if (currentSum + nums[idx] <= k) {
      boolean result = rec(idx + 1, k, nums, currentSum + nums[idx]);
      if (result) return result;
    }
    return rec(idx + 1, k, nums, currentSum);
  }
}
```

## Total Number of Subsequences with Sum K

- sum results from all branches
- base case - evaluate if condition is satisfied
- https://www.codingninjas.com/studio/problems/subset-sum_630213

```java
public class Solution {

  public static void main(String[] args) {
    new Solution().run(new int[]{1, 2, 1}, 2);
    new Solution().run(new int[]{1, 2, 1}, 5);
    new Solution().run(new int[]{1, 2, 1}, 3);
    new Solution().run(new int[]{1, 2, 1}, 1);
  }

  public void run(int[] nums, int k) {
    int result = rec(0, k, nums, 0);
    System.out.println(k + ": " + result);
  }

  private int rec(int idx, int k, int[] nums, int currentSum) {
    if (idx == nums.length) {
      return currentSum == k ? 1 : 0;
    }
    int result = 0;
    if (currentSum + nums[idx] <= k) {
      result += rec(idx + 1, k, nums, currentSum + nums[idx]);
    }
    result += rec(idx + 1, k, nums, currentSum);
    return result;
  }
}
```
