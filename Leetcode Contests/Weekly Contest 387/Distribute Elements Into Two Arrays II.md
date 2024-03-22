# Distribute Elements Into Two Arrays II

- https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/
- not entirely sure about time complexity
- todo - watch https://www.youtube.com/watch?v=zcwOff4MvbQ

```java
class Solution {

    public int[] resultArray(int[] nums) {
        
        List<Node> arr1 = new ArrayList<>();
        List<Node> arr2 = new ArrayList<>();
        
        arr1.add(new Node(nums[0], 0));
        arr2.add(new Node(nums[1], 0));
        
        int n = nums.length;
        
        for (int i = 2; i < n; i++) {
            
            int val1 = greaterCount(arr1, nums[i]);
            int idx1 = arr1.size() - val1;

            int val2 = greaterCount(arr2, nums[i]);
            int idx2 = arr2.size() - val2;

            if (val1 > val2) {
                arr1.add(idx1, new Node(nums[i], arr1.size()));
            } else if (val1 < val2) {
                arr2.add(idx2, new Node(nums[i], arr2.size()));
            } else {
                if (arr1.size() <= arr2.size()) {
                    arr1.add(idx1, new Node(nums[i], arr1.size()));
                } else {
                    arr2.add(idx2, new Node(nums[i], arr2.size()));
                }
            }

            // System.out.println(arr1 + ", " + arr2);
        }

        int[] result = new int[n];

        for (Node node : arr1) {
            result[node.idx] = node.val;
        }

        for (Node node : arr2) {
            result[node.idx + arr1.size()] = node.val;
        }

        return result;
    }
    
    private int greaterCount(List<Node> arr, int val) {

        int l = 0;
        int r = arr.size();

        int result = 0;

        while (l <= r) {
            
            int m = (l + r) / 2;

            if (m == arr.size()) {
                result = 0;
                break;
            } else if (arr.get(m).val > val) {
                result = arr.size() - m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return result;
    }

    static class Node {

        int val;
        int idx;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "(" + val + ", " + idx + ")";
        }
    }
}
```
