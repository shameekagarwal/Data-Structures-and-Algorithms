# Find Median from Data Stream

- second half (the larger half) is a min heap
- first half (the smaller half) is a max heap
- when total size (length of first half + length of second half) is odd 
  - add incoming element to the second half
  - remove top element from the second half and add it to the first half
- this ensures both halves are of same and equal size, and priority queues are also "valid"
- when total size (length of first half + length of second half) is even
  - add incoming element to the first half
  - remove top element from the first half and add it to the second half
- this ensures both halves obey our original criteria - number of elements in the second half are one more than the elements in the first half - and the priority queues are also "valid"

```java
class MedianFinder {

    PriorityQueue<Integer> firstHalf;
    PriorityQueue<Integer> secondHalf;

    public MedianFinder() {
        firstHalf = new PriorityQueue<>(Collections.reverseOrder());
        secondHalf = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (isEvenSize()) {
            firstHalf.add(num);
            secondHalf.add(firstHalf.remove());
        } else {
            secondHalf.add(num);
            firstHalf.add(secondHalf.remove());
        }
    }
    
    public double findMedian() {
        if (isEvenSize()) {
            return (0L + firstHalf.peek() + secondHalf.peek()) / 2.0;
        } else {
            return secondHalf.peek();
        }
    }

    private boolean isEvenSize() {
        return ((firstHalf.size() + secondHalf.size()) % 2) == 0;
    }
}
```
