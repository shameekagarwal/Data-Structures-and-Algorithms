# 4-Sum Problem

- exactly same as [3-Sum Problem](./3-Sum%20Problem.md), just an extra nested loop is needed

```java
class Solution {
    public List<List<Integer>> fourSum(int[] numsRaw, int target) {
        
        int[] nums = Arrays.copyOfRange(numsRaw, 0, numsRaw.length);
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length;) {
            for (int j = i + 1; j < nums.length;) {
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    long currentSum = nums[i] + 0L + nums[j] + nums[k] + nums[l];
                    if (currentSum < target) {
                        int currentK = nums[k];
                        while (k < l && currentK == nums[k]) {
                            k += 1;
                        }
                    } else if (currentSum > target) {
                        int currentL = nums[l];
                        while (k < l && currentL == nums[l]) {
                            l -= 1;
                        }
                    } else {
                        result.add(List.of(nums[i], nums[j], nums[k], nums[l]));
                        int currentK = nums[k];
                        while (k < l && currentK == nums[k]) {
                            k += 1;
                        }
                        int currentL = nums[l];
                        while (k < l && currentL == nums[l]) {
                            l -= 1;
                        }
                    }
                }
                int currentJ = nums[j];
                while (j < nums.length && nums[j] == currentJ) {
                    j += 1;
                }
            }
            int currentI = nums[i];
            while (i < nums.length && nums[i] == currentI) {
                i += 1;
            }
        }

        return result;
    }
}
```
