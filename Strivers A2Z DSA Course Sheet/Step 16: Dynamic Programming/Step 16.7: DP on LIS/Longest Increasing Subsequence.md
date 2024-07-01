# Longest Increasing Subsequence

- https://leetcode.com/problems/longest-increasing-subsequence/
- we need to keep track of current index, previous index part of subsequence
- the options are typical subsequence options - take / not take
- for dp, since our previous index can be -1 when no elements are picked - do a coordinate change by adding a +1
- time complexity - n^2, space complexity of optimized tabulation - 2*n

```java
class Solution {

    public int lengthOfLIS(int[] arr) {
    
        int[][] memo = new int[arr.length][arr.length + 1];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return lengthOfLIS(arr, 0, -1, memo);
    }

    private static int lengthOfLIS(int arr[], int currIdx, int lastPickedIdx, int[][] memo) {

        if (currIdx == arr.length) {
            return 0;
        }

        if (memo[currIdx][lastPickedIdx + 1] != -1) {
            return memo[currIdx][lastPickedIdx + 1];
        }

        int result = lengthOfLIS(arr, currIdx + 1, lastPickedIdx, memo);

        if (lastPickedIdx == -1 || arr[lastPickedIdx] < arr[currIdx]) {
            result = Math.max(result, lengthOfLIS(arr, currIdx + 1, currIdx, memo) + 1);
        }

        memo[currIdx][lastPickedIdx + 1] = result;

        return result;
    }
}
```

- tabular + space optimized

```java
class Solution {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        int[][] dp = new int[2][n + 1];

        for (int i = 0; i < n; i++) {

            int row = i & 1;
            int prevRow = 1 - row;

            for (int j = -1; j < i; j++) {

                dp[row][j + 1] = i == 0 ? 0 : dp[prevRow][j + 1];

                if (j == -1 || nums[j] < nums[i]) {
                    dp[row][i + 1] = Math.max(dp[row][i + 1], i == 0 ? 1 : dp[prevRow][j + 1] + 1);
                }
            }
        }

        int lis = 0;
        for (int i : dp[(n - 1) & 1]) {
            lis = Math.max(lis, i);
        }
        return lis;
    }
}
```

## Better

- for every i, `lis[i] = max(lis[j] + 1)`, where j goes from 0 to i - 1 and `nums[j] < nums[i]`
- this helps in [printing](./Printing%20Longest%20Increasing%20Subsequence.md)
- time complexity - O(n^2), space complexity - O(n)

```java
class Solution {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int lis = 0;
        for (int i : dp) {
            lis = Math.max(lis, i);
        }
        return lis;
    }
}
```

## Binary Search

- put an element at its lower bound
- imagine we are at i = 3
- case 1 - 1 7 8 4 9 11
- lis till i = 2 - 1 7 8
- now, we can go and put 4 in place of 7, so lis becomes 1 4 8
- if an lis had to use 1 7, it could "also work" with 1 4
- case 2 - 1 7 8 4 5 6 7 8 9
- now, same thing - we can go and put 4 in place of 7, so lis becomes 1 4 8
- if an lis had to use 1 4, we are "on track" by continuing with our logic
- for equal - we try "overwriting" - thats why my logic is `arr.get(m) >= element`
- remember - lis helper is not the lis - it just helps get the length
- todo - time complexity - why do they call this n log n, cannot understand

```java
import java.util.List;
import java.util.ArrayList;

public class Solution {

	public static int longestIncreasingSubsequence(int arr[]) {

		List<Integer> lisHelper = new ArrayList<>();

		int n = arr.length;

		for (int i = 0; i < n; i++) {
			insert(lisHelper, arr[i]);
		}

		return lisHelper.size();
	}

	private static void insert(List<Integer> list, int element) {
		
		int l = 0;
		int r = list.size();

		int position = -1;

		while (l <= r) {
			
			int m = l + ((r - l) / 2);
			if (m == list.size()) {
				position = m;
				break;
			} else if (list.get(m) >= element) {
				position = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}

		if (position == list.size()) {
			list.add(element);
		} else {
			list.set(position, element);
		}
	}
}
```
