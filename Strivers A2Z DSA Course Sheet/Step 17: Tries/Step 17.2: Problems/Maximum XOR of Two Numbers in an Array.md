# Maximum XOR of Two Numbers in an Array

- https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
- inserting a number - we store it in its 32 bit format (msb towards root)
- now, again start iterating over all elements
- try to move in the direction opposite to the element's bit - also, add the contribution to the xor
- if not possible, move in the same direction

```java
class Solution {

    public int findMaximumXOR(int[] nums) {

        Node head = new Node();

        for (int num : nums) {
            
            Node current = head;

            for (int i = 31; i > -1; i--) {

                int bit = ((num >> i) & 1);

                if (current.ptr[bit] == null) {
                    current.ptr[bit] = new Node();
                }

                current = current.ptr[bit];
            }
        }

        int result = 0;

        for (int num : nums) {
            
            Node current = head;
            int currentMaxXor = 0;

            for (int i = 31; i > -1; i--) {

                int bit = ((num >> i) & 1);

                if (current.ptr[1 - bit] != null) {
                    currentMaxXor |= (1 << i);
                    current = current.ptr[1 - bit];
                } else {
                    current = current.ptr[bit];
                }
            }

            result = Math.max(result, currentMaxXor);
        }

        return result;
    }

    static class Node {

        Node[] ptr = new Node[2];
    }
}
```
