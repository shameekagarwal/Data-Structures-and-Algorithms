# Task Scheduler

- https://leetcode.com/problems/task-scheduler/
- doing this using greedy approach felt more intuitive than forcing priority queue in this
- greedy approach - assume we have tasks like this - a a a b b b c c c d d e, n = 5
- the optimal way to place these tasks is a b c _ _ _ a b c _ _ _ a b c
- i.e. place the tasks with the max frequency together with the "remaining cooldown gaps" between them
- edge case - if n is 1, but we have e.g. 10 tasks with max frequency - so number of tasks with max frequency exceed the cooldown period. so, we just return the number of tasks
- normal case - cool down gaps left = the formula below (based on observation)
- what if more tasks are remaining than the gaps left? e.g. a a a b b b c c c d e f g, n = 3
- optimal way is a b c _ a b c _ a b c, so we have 2 cooldown gaps left but 4 tasks remaining

```java
class Solution {

    public int leastInterval(char[] tasks, int n) {

        int[] characterCount = new int['Z' - 'A' + 1];
        for (char task : tasks) {
            characterCount[task - 'A'] += 1;
        }

        int maxFrequency = 0;
        int tasksWithMaxFrequency = 0;
        for (int frequency : characterCount) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                tasksWithMaxFrequency = 1;
            } else if (frequency == maxFrequency) {
                tasksWithMaxFrequency += 1;
            }
        }

        if (tasksWithMaxFrequency > n) {
            return tasks.length;
        }

        int cooldownGapsLeft = (n - (tasksWithMaxFrequency - 1)) * (maxFrequency - 1);
        int taskInstancesWithMaxFrequency = tasksWithMaxFrequency * maxFrequency; 
        return taskInstancesWithMaxFrequency + Math.max(tasks.length - taskInstancesWithMaxFrequency, cooldownGapsLeft);
    }
}
```
