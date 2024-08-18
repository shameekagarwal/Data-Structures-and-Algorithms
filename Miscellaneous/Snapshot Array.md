# Snapshot Array

- https://leetcode.com/problems/snapshot-array/
- for every index we store a list
- the list stores (value, last_snap_id) combination
- lets say we take a snap has id x
- for all indexes, the last element will have the snap id set as x-1 or lesser
- since the snap id can ony monotonically increase, we can perform a binary search on this

```java
class SnapshotArray {

    private int time;
    private List<List<int[]>> list;
    private List<Integer> snaps;

    public SnapshotArray(int length) {

        list = new ArrayList<>();
        snaps = new ArrayList<>();
        time = 0;

        for (int i = 0; i < length; i++) {
            list.add(new ArrayList<>());
            list.get(i).add(new int[]{0, time});
        }
    }

    public void set(int index, int val) {

        time += 1;

        list.get(index).add(new int[]{val, time});
    }

    public int snap() {

        time += 1;

        snaps.add(time);
        return snaps.size() - 1;
    }

    public int get(int index, int snap_id) {

        int timeOfSnap = snaps.get(snap_id);

        int l = 0;
        int r = list.get(index).size() - 1;
        int result = 0;

        while (l <= r) {

            int m = (l + r) / 2;

            if (list.get(index).get(m)[1] < timeOfSnap) {
                l = m + 1;
                result = m;
            } else {
                r = m - 1;
            }
        }

        return list.get(index).get(result)[0];
    }
}
```
