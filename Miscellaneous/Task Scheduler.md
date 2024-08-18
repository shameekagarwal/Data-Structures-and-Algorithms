# Task Scheduler

- https://leetcode.com/problems/task-scheduler/

## Approach 1

- complexity - O(26 * 2 * N) ish
- keep track of two things for every character - 
  - frequency of character remaining to be scheduled
  - next possible time to schedule
- keep track of the current time as well
- try to find a task - 
  - next possible time <= current time
  - with greatest frequency among these
- suppose we could not find any such task - bump up current time to the smallest value in the next possible time lookup
- else, schedule this task and bump up current time by 1
- also, decrement the frequency lookup and adjust the next possible time

```java
class Solution {

    private static final int NO_OF_CHARS = 'Z' - 'A' + 1;

    public int leastInterval(char[] tasks, int cooldown) {

        int[] frequencyOfTasks = new int[NO_OF_CHARS];

        for (int i = 0; i < tasks.length; i++) {
            frequencyOfTasks[tasks[i] - 'A'] += 1;
        }

        int[] nextPossibleTimeOfTasks = new int[NO_OF_CHARS];

        int currentTime = 0;

        int tasksRemaining = tasks.length;

        while (tasksRemaining > 0) {

            int index = -1;

            for (int i = 0; i < NO_OF_CHARS; i++) {

                if (frequencyOfTasks[i] > 0 && currentTime >= nextPossibleTimeOfTasks[i]) {

                    if (index == -1 || frequencyOfTasks[i] > frequencyOfTasks[index]) {
                        index = i;
                    }
                }
            }

            if (index == -1) {

                int newCurrentTime = currentTime;

                for (int i = 0; i < NO_OF_CHARS; i++) {

                    if (frequencyOfTasks[i] > 0) {
                        if (newCurrentTime == currentTime) {
                            newCurrentTime = nextPossibleTimeOfTasks[i];
                        } else {
                            newCurrentTime = Math.min(newCurrentTime, nextPossibleTimeOfTasks[i]);
                        }
                    }
                }

                currentTime = newCurrentTime;

                continue;
            }

            tasksRemaining -= 1;
            frequencyOfTasks[index] -= 1;
            nextPossibleTimeOfTasks[index] += (cooldown + 1);

            // System.out.printf("(%c, %d) ", (char)(index + 'A'), currentTime);

            currentTime += 1;
        }

        // System.out.println();

        return currentTime;
    }
}
```

## Approach 2

- complexity - strictly O(N)
- find "tasks with max frequency" and "max frequency"
- the best way is to arrange them like this -
  ```
  A B C _ _ _ A B C _ _ _ A B C _ _ _ A B C
  ```
- edge case - if "tasks with max frequency" > cooldown, we can be sure that there would be no idle spaces - so, we can simply return tasks.length
- for all other cases, use the algorithm below
- "cycle lengths" are cooldown + 1. here, a cycle refers to below
  ```
  A B C _ _ _
  ```
- so, "remaining gaps in a cycle" = cooldown + 1 - "tasks with max frequency"
- "remaining gaps" = "remaining gaps in a cycle" * (maxFrequency - 1)
- similarly, we calculate other parameters described in code below to obtain the final result

```java
class Solution {

    private static final int NO_OF_CHARS = 'Z' - 'A' + 1;

    public int leastInterval(char[] tasks, int cooldown) {

        int[] frequencyOfTasks = new int[NO_OF_CHARS];

        for (int i = 0; i < tasks.length; i++) {
            frequencyOfTasks[tasks[i] - 'A'] += 1;
        }

        int tasksWithMaxFrequency = 0;
        int maxFrequency = 0;

        for (int i = 0; i < NO_OF_CHARS; i++) {

            if (maxFrequency < frequencyOfTasks[i]) {
                maxFrequency = frequencyOfTasks[i];
                tasksWithMaxFrequency = 1;
            } else if (maxFrequency == frequencyOfTasks[i]) {
                tasksWithMaxFrequency += 1;
            }
        }

        if (cooldown < tasksWithMaxFrequency) {
            return tasks.length;
        }

        int remainingGaps = (cooldown + 1 - tasksWithMaxFrequency) * (maxFrequency - 1);
        int lastTimeOfTasksWithMaxFrequency = (cooldown + 1) * (maxFrequency - 1) + (tasksWithMaxFrequency - 1) + 1;
        int gapsOccupiedByTasksWithMaxFrequency = maxFrequency * tasksWithMaxFrequency;
        int remainingTasks = tasks.length - gapsOccupiedByTasksWithMaxFrequency;

        return remainingTasks <= remainingGaps ?
            lastTimeOfTasksWithMaxFrequency :
            lastTimeOfTasksWithMaxFrequency + (remainingTasks - remainingGaps);
    }
}
```
