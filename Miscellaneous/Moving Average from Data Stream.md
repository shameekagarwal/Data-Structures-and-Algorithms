# Moving Average from Data Stream

- https://leetcode.com/problems/moving-average-from-data-stream/
- maintain the current window in a queue
- if queue length exceeds size, remove the oldest element from the queue
- subtract this oldest element from the running total

```java
class MovingAverage {

    private double sum;
    private int size;
    private Deque<Integer> queue;

    public MovingAverage(int size) {
        sum = 0;
        this.size = size;
        queue = new ArrayDeque<>();
    }

    public double next(int val) {

        sum += val;
        queue.add(val);

        if (queue.size() > size) {
            sum -= queue.removeFirst();
        }

        return sum / queue.size();
    }
}
```
