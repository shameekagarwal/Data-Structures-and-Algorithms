# Remove K Digits

- https://leetcode.com/problems/remove-k-digits/
- we try to be "greedy" about this
- ultimately, the number would have n - k digits left,and we have to use the lowest possible value for these n 0- k digits. for this, we try to minimize msb first and then remove from lsb
- e.g. i have a number 54321. observe
  - if k is 1, i would remove 5
  - if k is 2, i would remove 5 and 4
  - and so on
- now, assume, if i have a number 12345. observe
  - if k is 1, i would remove 5
  - if k is 2, i would remove 5 and 4
  - and so on
- so, idea is to first try and minimizing msb
- "if we still have k left after doing this", we try removing from lsb
- so, my understanding - we will never reach the condition of removing from the end till the entire string does not has become monotonically increasing
- now, a corner case i made mistake in - when popping from the stack, i was using `deque.peekLast() >= digit` and not `deque.peekLast() > digit`
- when this would fail - e.g. if input is 11223344
- earlier algorithm will make it 1223344, while the correct one would make it 1122334
- this means that if we also try to pop for equal, we are actually increasing the value at the 2nd msb (from 1 to 2), because the next digit would be larger (we are trying to move towards monotonic increasing)

```java
class Solution {

    public String removeKdigits(String num, int k) {
        
        Deque<Short> deque = new ArrayDeque<>();
        char[] digits = num.toCharArray();
        
        for (int i = 0; i < digits.length; i++) {
            short digit = (short) (digits[i] - '0');
            while (k > 0 && !deque.isEmpty() && deque.peekLast() > digit) {
                deque.removeLast();
                k -= 1;
            }
            deque.addLast(digit);
        }

        while (k > 0 && !deque.isEmpty()) {
            deque.removeLast();
            k -= 1;
        }

        while (!deque.isEmpty() && deque.peekFirst() == 0) {
            deque.removeFirst();
        }
        if (deque.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.removeFirst());
        }

        return sb.toString();
    }
}
```
