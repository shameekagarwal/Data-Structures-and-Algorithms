# Most Frequent IDs

- https://leetcode.com/problems/most-frequent-ids/

## My Solution

- `numLookup` - is a hash map - contains frequency for all numbers seen up to i
- `frequencyLookup` - is a tree map - contains map of (frequency, count of elements with the frequency)
- now, every time when we encounter `nums[i]`, lets say older frequency is `x`, new frequency is `x + freq[i]`
- in frequency lookup - 
  - decrement old frequency by 1
  - increment new frequency by 1
- if number of elements for old frequency goes to 0 after decrement, remove it from the tree map
- finally, the key of the first element in tree map will contain the lowest frequency

```java
class Solution {
    
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        
        Map<Integer, Long> numLookup = new HashMap<>();
        Map<Long, Integer> frequencyLookup = new TreeMap<>(Collections.reverseOrder());
        int n = nums.length;
        long[] result = new long[n];
        
        for (int i = 0; i < n; i++) {

            long olderFrequency = numLookup.getOrDefault(nums[i], 0L);
            long newerFrequency = olderFrequency + freq[i];

            numLookup.put(nums[i], newerFrequency);
            
            frequencyLookup.put(olderFrequency, frequencyLookup.getOrDefault(olderFrequency, 1) - 1);
            if (frequencyLookup.get(olderFrequency) == 0) {
                frequencyLookup.remove(olderFrequency);
            }
            frequencyLookup.put(newerFrequency, frequencyLookup.getOrDefault(newerFrequency, 0) + 1);
            
            result[i] = frequencyLookup.entrySet().iterator().next().getKey();
        }
        
        return result;
    }
}
```
