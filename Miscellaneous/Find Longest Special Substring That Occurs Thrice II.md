# Find Longest Special Substring That Occurs Thrice II

- https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/
- for every character - maintain their continuous windows - i use a max heap, so that i can get the three biggest elements at the end
  - optionally, i could maintain a min heap and keep its size capped at three. logic might change a little, the concept stays the same
- now, iterate over all these counts
- if count is 1 - our max can be count-2, if count is >= 3
- if count is 2 -
  - say both counts are same - we can construct count-1 three times if count >= 2
  - say they are unequal - we can construct the smaller one three times if the smaller count >= 1
- if count is 3 - we have already constructed count-1 and count-2 if possible - the only possible way to exceed is just using count. this is only possible when all of them hold the same value

```java
class Solution {

    public int maximumLength(String s) {
        return maximumLength(s.toCharArray());
    }

    public int maximumLength(char[] arr) {

        Map<Character, PriorityQueue<Integer>> map = new HashMap<>();

        int l = 0;
        int r = 0;

        while (r < arr.length) {

            while (r < arr.length && arr[l] == arr[r]) {
                r += 1;
            }

            if (!map.containsKey(arr[l])) {
                map.put(arr[l], new PriorityQueue<>(Collections.reverseOrder()));
            }

            map.get(arr[l]).add(r - l);

            if (map.get(arr[l]).size() > 3) {
                map.get(arr[l]).remove();
            }

            l = r;
        }

        int result = -1;

        for (PriorityQueue<Integer> counts : map.values()) {

            int a, b, c;

            if (counts.size() >= 1) {
                
                a = counts.remove();

                if (a >= 3) {
                    result = Math.max(result, a - 2);
                }

                if (counts.size() >= 1) {

                    b = counts.remove();

                    if (a == b && a >= 2) {
                        result = Math.max(result, a - 1);
                    } else if (a > b && b >= 1) {
                        result = Math.max(result, b);
                    }

                    if (counts.size() >= 1) {

                        c = counts.remove();

                        if (a == b && a == c) {
                            result = Math.max(result, a);
                        }
                    }
                }
            }
        }

        return result;
    }
}
```
