# Program for Least Recently Used

- https://www.geeksforgeeks.org/problems/page-faults-in-lru5603/1
- i solved it just like [LRU Cache](../../Step%209:%20Stack%20and%20Queues/Step%209.4:%20Implementation%20Problems/LRU%20Cache.md)

```java
class Solution{
    static int pageFaults(int N, int C, int pages[]){
        Set<Integer> set = new LinkedHashSet<>();
        int pageFaults = 0;
        for(int page : pages) {
            boolean isPageFault = true;
            if (set.contains(page)) {
                isPageFault = false;
                set.remove(page);
            } else if (set.size() == C) {
                set.remove(set.iterator().next());
            }
            set.add(page);
            // System.out.println(set);
            pageFaults += isPageFault ? 1 : 0;
        }
        return pageFaults;
    }
}
```
