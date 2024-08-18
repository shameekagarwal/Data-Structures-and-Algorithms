# Logger Rate Limiter

- https://leetcode.com/problems/logger-rate-limiter/
- simple solution - use hash map to keep track of previous timestamp for a string
- if the previous time the timestamp was seen, was lesser than current timestamp - 10, then return false
- else, update timestamp and return true
- advanced, optimal - use linked hash map to help prune
- while pruning, i am able to "break" out of the loop since this is a linked hash map - values will be inserted in order of timestamp automatically
- since size of 10 is small anyway, hash map might have sufficed as well, depends on limits

```java
class Logger {

    private Map<String, Integer> lookup;

    public Logger() {
        lookup = new LinkedHashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        
        List<String> toRemove = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : lookup.entrySet()) {
            if (entry.getValue() + 10 <= timestamp) {
                toRemove.add(entry.getKey());
            } else {
                break;
            }
        }

        // System.out.printf("%s, %s, ", lookup, toRemove);
        toRemove.forEach(lookup::remove);
        // System.out.printf("%s\n", lookup);

        if (lookup.containsKey(message)) {
            return false;
        }

        lookup.put(message, timestamp);
        return true;
    }
}
```
