# Implement Queue using Arrays

- we use two pointers - front and rear
- both start from 0
- if both are pointing to the same element - we can assume that the queue is empty
- if `(rear + 1) % length == front`, we can assume that the queue if full
- to avoid handling dynamic sizing to make implementation in interviews easier, we declare array of fixed but large length upfront
- printing all elements of queue - go from front to rear
- https://www.codingninjas.com/studio/problems/implement-queue-using-arrays_8390825

```java
public class Solution {

    class Queue {

        private static final int SIZE = 100001;

        int front, rear;
        int []arr;

        Queue() {
            front = 0;
            rear = 0;
            arr = new int[SIZE];
        }

        public void enqueue(int e) {
            if (isFull()) return;
            arr[rear] = e;
            rear = (rear + 1) % SIZE;
        }

        public int dequeue() {
            if (isEmpty()) return -1;
            int ele = arr[front];
            front = (front + 1) % SIZE;
            return ele;
        }

        private boolean isFull() {
            return ((rear + 1) % SIZE) == front;
        }

        private boolean isEmpty() {
            return rear == front;
        }
    }
}
```
