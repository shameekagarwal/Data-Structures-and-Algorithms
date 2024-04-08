# Maximum XOR With an Element From Array

- https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
- this is also called "offline queries"
- we can sort the array
- we can sort the queries based on 2nd element
- remember to keep track of the index when sorting queries to be able to populate the result later
- remember - trie will have nulls in nodes if mi is too low, because nothing from nums gets added to the trie - so, place the pre check, and question says to use -1 for it

```java
class Solution {

    public int[] maximizeXor(int[] nums, int[][] queries) {

        int n = nums.length;
        Arrays.sort(nums);

        int q = queries.length;
        int[][] sortedQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i][0];
            sortedQueries[i][1] = queries[i][1];
            sortedQueries[i][2] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> a[1] - b[1]);

        Trie trie = new Trie();

        int ptr = 0;
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {

            while ((ptr < n) && (nums[ptr] <= sortedQueries[i][1])) {
                trie.insert(nums[ptr]);
                ptr += 1;
            }

            // no numbers inserted in trie yet
            // because mi is very low
            if (ptr == 0) {
                result[sortedQueries[i][2]] = -1;
                continue;    
            }

            result[sortedQueries[i][2]] = trie.maxXor(sortedQueries[i][0]);
        }

        return result;
    }

    static class Trie {

        Node head = new Node();

        public void insert(int num) {

            Node current = head;

            for (int i = 31; i > -1; i--) {

                int bit = (num >> i) & 1;

                if (current.ptr[bit] == null) {
                    current.ptr[bit] = new Node();
                }

                current = current.ptr[bit];
            }
        }

        public int maxXor(int num) {

            Node current = head;

            int result = 0;

            for (int i = 31; i > -1; i--) {

                int bit = (num >> i) & 1;

                if (current.ptr[1 - bit] != null) {
                    current = current.ptr[1 - bit];
                    result |= (1 << i);
                } else {
                    current = current.ptr[bit];
                }
            }

            return result;
        }
    }

    static class Node {

        Node[] ptr = new Node[2];
    }
}
```
