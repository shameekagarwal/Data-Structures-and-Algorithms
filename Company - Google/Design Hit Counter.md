# Design Hit Counter

- https://leetcode.com/problems/design-hit-counter/
- use a queue - 
  - add to the end of it
  - keep removing from the beginning of it till there are elements in the queue with timestamp + 300 <= incoming timestamp
- time complexity - 
  - adding to hit is O(1)
  - get hits might feel like O(N), but amortized is O(1) because an element is only processed twice at most

```java
class HitCounter {

    private Deque<Integer> hits;

    public HitCounter() {
        hits = new ArrayDeque<>();
    }
    
    public void hit(int timestamp) {
        hits.addLast(timestamp);
    }
    
    public int getHits(int timestamp) {

        while (!hits.isEmpty() && hits.peekFirst() + 300 <= timestamp) {
            hits.removeFirst();
        }

        return hits.size();
    }
}
```
