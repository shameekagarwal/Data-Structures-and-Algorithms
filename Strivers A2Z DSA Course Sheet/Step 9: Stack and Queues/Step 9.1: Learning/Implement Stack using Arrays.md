# Implement Stack using Arrays

- https://www.codingninjas.com/studio/problems/stack-implementation-using-array_3210209
- ptr can go up to `arr.length - 1`
- ptr would be -1 when stack is empty
- for every insertion, increment pointer and then put element in `arr[ptr]`
- note if asked in interview - remember to have capacity in constructor to get rid  of issues like dynamic sizing etc
- also, remember the check of isFull that is needed if we initialize using capacity and do not handle dynamic sizing
- look reusing of isEmpty / isFull in other functions, reusing of top inside pop, etc

```java
import java.util.Arrays;

public class Solution {

    static class Stack {

        private int[] arr;
        private int ptr;

        Stack(int capacity) {
            arr = new int[capacity];
            ptr = -1;
        }

        private void print() {
            System.out.printf("stack: %s, ptr: %d\n", Arrays.toString(arr), ptr);
        }

        public void push(int num) {
            if (isFull() == 0) {
                ptr += 1;
                arr[ptr] = num;
            }
        }

        public int pop() {
            int ele = top();
            if (isEmpty() != 1) ptr -= 1;
            return ele;
        }

        public int top() {
            return isEmpty() == 1 ? -1 : arr[ptr];
        }

        public int isEmpty() {
            return ptr == -1 ? 1 : 0;
        }

        public int isFull() {
            return ptr == arr.length - 1 ? 1 : 0;
        }
    }
}
```
