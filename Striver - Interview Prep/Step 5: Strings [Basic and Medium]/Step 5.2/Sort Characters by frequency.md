# Sort Characters by frequency

- https://leetcode.com/problems/sort-characters-by-frequency/
- notice the use of string builder - which results in efficient runtime

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
