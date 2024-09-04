# Hand of Straights

- https://leetcode.com/problems/hand-of-straights/
- edge case - if n % k != 0, return 0
- try forming first group greedily - using the k minimum elements possible
  - every element has to be a part of same group - so does the minimum element
  - the minimum element would be the starting of its group, and we need all elements - minimum element, minimum element + 1, minimum element + 2 .... minimum element + k - 1
- when forming a group - we extract the minimum element fom the priority queue
- but if we find duplicates, we store it in an auxiliary array, and then add it back after the current group has been formed
- if the previous min != current min - 1, return false
- edge case i missed initially - group size = 3, hands = [1, 1, 1] - the priority queue will become empty when trying to pick the second element for the first group - so, return false if the priority queue is empty as well
- time complexity - O(n) (for the initial heap construction) + O((n / k) * (k * log(N))) - the outer loop runs n / k times, and each loop has a nested loop of size k extracting k times from heap
- but again, i think heap size decreases since we extract k elements from it every time

```java
class Solution {

    public boolean isNStraightHand(int[] hand, int groupSize) {

        if (hand.length % groupSize != 0) return false;
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int card : hand) {
            minHeap.add(card);
        }

        while (!minHeap.isEmpty()) {
            
            List<Integer> remains = new ArrayList<>();
            int previousElement = minHeap.peek() - 1; // so that it passes for first element of group

            for (int i = 0; i < groupSize; i++) {

                if (minHeap.isEmpty()) return false;
                int currentElement = minHeap.remove();
                if (currentElement != previousElement + 1) return false;

                while (!minHeap.isEmpty() && minHeap.peek() == currentElement) {
                    remains.add(minHeap.remove());
                }
                previousElement = currentElement;
            }

            for (int remain : remains) {
                minHeap.add(remain);
            }
        }

        return true;
    }
}
```

- i think above solution is a little (better ?) if implemented using tree map

```java
class Solution {

    public boolean isNStraightHand(int[] hand, int groupSize) {

        if (hand.length % groupSize != 0) {
            return false;
        }

        int noOfGroups = hand.length / groupSize;

        Map<Integer, Integer> frequency = new TreeMap<>();

        for (int i = 0; i < hand.length; i++) {
            frequency.put(hand[i], frequency.getOrDefault(hand[i], 0) + 1);
        }

        for (int i = 0; i < noOfGroups; i++) {

            int toPick = frequency.entrySet().iterator().next().getKey();

            // System.out.printf("group %d: ", i + 1);

            for (int j = 0; j < groupSize; j++) {

                if (!frequency.containsKey(toPick)) {
                    return false;
                }

                // System.out.printf("%d, ", toPick);

                frequency.put(toPick, frequency.get(toPick) - 1);

                if (frequency.get(toPick) == 0) {
                    frequency.remove(toPick);
                }

                toPick += 1;
            }

            // System.out.printf("\n");
        }

        return true;
    }
}
```

- another solution - sorting
- store the array of counts inside a map
- sort the array
- look if `hands[i]` is non zero. if it is, we start constructing a group for it
- if we are unable to, we return false
- this might run slightly better, maybe because of the complexity in min heap around deduping

```java
class Solution {

    public boolean isNStraightHand(int[] hand, int groupSize) {

        if (hand.length % groupSize != 0) return false;
        
        hand = Arrays.copyOfRange(hand, 0, hand.length);
        Arrays.sort(hand);
        
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int card : hand) {
            frequency.put(card, frequency.getOrDefault(card, 0) + 1);
        }

        for (int i = 0; i < hand.length; i++) {
            
            if (!frequency.containsKey(hand[i])) continue;

            for (int card = hand[i]; card < hand[i] + groupSize; card++) {

                if (!frequency.containsKey(card)) return false;
                
                int currentFrequency = frequency.get(card);
                if (currentFrequency == 1) frequency.remove(card);
                else frequency.put(card, currentFrequency - 1);
            }
        }

        return true;
    }
}
```
