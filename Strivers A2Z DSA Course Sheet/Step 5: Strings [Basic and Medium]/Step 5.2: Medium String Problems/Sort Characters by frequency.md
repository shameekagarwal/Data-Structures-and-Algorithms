# Sort Characters by frequency

- https://leetcode.com/problems/sort-characters-by-frequency/
- notice the use of string builder - which results in efficient runtime
- wasted a lot of time trying to use tree map - remember, tree map sorts by key and not value

## Naive - O(N log N)

- maybe instead of sorting if we add to a heap, complexity would reduce to an asymptotic O(N)

```java
class Solution {
    
    public String frequencySort(String s) {
        
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        List<Pair> counts = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            counts.add(new Pair(entry.getKey(), entry.getValue()));
        }
        Collections.sort(counts, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (Pair p : counts) {
            for (int i = 0; i < p.count; i++) {
                sb.append(p.c);
            }
        }
        return sb.toString();
    }
}

class Pair implements Comparable<Pair> {

    char c;
    int count;

    Pair(char c, int count) {
        this.c = c;
        this.count = count;
    }

    @Override
    public int compareTo(Pair pair) {
        return count - pair.count;
    }

    @Override
    public String toString() {
        return "(" + c + ", " + count + ")";
    }
}
```

## Optimal - O(N)

- use "bucket sort" - important technique when we know range is within 1 to n
- this means that it can fit inside an array
- then, we can iteratee over the array

```java
class Solution {

    public String frequencySort(String s) {

        Map<Character, Integer> frequency = new HashMap<>();
        char[] arr = s.toCharArray();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            frequency.put(arr[i], frequency.getOrDefault(arr[i], 0) + 1);
        }

        List<List<Character>> buckets = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        StringBuilder result = new StringBuilder();

        for (int i = n; i > 0; i--) {
            
            for (char c : buckets.get(i)) {

                for (int j = 0; j < i; j++) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
```
