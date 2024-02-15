# Online Stock Span

- https://leetcode.com/problems/online-stock-span/
- we do not need any price that is lesser than incoming element
- if a price is greater than 29, it would be greater than 23
- so, we solve it like [previous greater element](../Step%209.3:%20Monotonic%20Stack%20Queue%20Problems%20/Next%20Greater%20Element.md)
- since i do not have the array handy - i cannot just store indices like usual, and obtain the element using `arr[stack.peekLast()]`
- so, i instead enqueue the combination of idx and price
- an element is always added to the stack (maybe after popping off some elements)
- so, the index of the next element would be the index of the element at the top of the stack + 1

```java
class StockSpanner {

    private static class PriceMetric {

        public int price;
        public int idx;

        PriceMetric(int price, int idx) {
            this.price = price;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "(price: " + price + ", idx: " + idx + ")";
        }
    }

    private Deque<PriceMetric> pge;

    public StockSpanner() {
        pge = new ArrayDeque<>();
    }

    public int next(int price) {
        int idx = pge.isEmpty() ? 0 : pge.peekLast().idx + 1;
        while (!pge.isEmpty() && pge.peekLast().price <= price) {
            pge.removeLast();
        }
        int totalDays = idx - (pge.isEmpty() ? -1 : pge.peekLast().idx);
        pge.addLast(new PriceMetric(price, idx));
        // System.out.println(pge);
        return totalDays;
    }
}
```
