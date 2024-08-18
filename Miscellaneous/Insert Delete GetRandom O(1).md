# Insert Delete GetRandom O(1)

- https://leetcode.com/problems/insert-delete-getrandom-o1/
- insert and remove can be done in O(1) using a simple hash set
- however, there is no "indexing" in hash set, so getting a random element is tough - we would have to iterate through all the entries of the hash set, which is effectively O(n)
- we can use a list for the random - but the insert and delete in lists can be O(n)
- how to make insert and deletes in list O(1)
  - insert - always at end
  - when asked to delete  
    - convert hash set to a hash map - maintains element, idx lookup
    - swap last element of list with index to delete (obtained from hash map above)
    - delete last element - list.remove(list.size() - 1) is O(1)
- so, deleting last element from a list in O(1) is possible by swapping with last element and deleting last element - if we are allowed to change the ordering

```java
class RandomizedSet {

    private Map<Integer, Integer> lookup;
    private List<Integer> list;

    public RandomizedSet() {
        lookup = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {

        if (lookup.containsKey(val)) {
            return false;
        }

        lookup.put(val, lookup.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {

        if (!lookup.containsKey(val)) {
            return false;
        }

        int idx = lookup.get(val);
        int lastElement = list.get(list.size() - 1);

        list.set(idx, lastElement);
        lookup.put(lastElement, idx);

        lookup.remove(val);
        list.remove(list.size() - 1);

        return true;
    }

    public int getRandom() {
        int idx = (int) Math.floor(Math.random() * lookup.size());
        return list.get(idx);
    }
}
```
