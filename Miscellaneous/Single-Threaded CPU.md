# Single-Threaded CPU

- also called sjf - shortest job first
- created a class such that it can store idx as well - idx, enqueue time and processing time
- first, sort by enqueue times - this way, suppose we are at time t and idx i - we can add all jobs that from idx i to say idx j, such that all tasks from i to j have enqueue times less than or equal to t
- now, we use a min heap, which we can compare using the condition mentioned - first poll by smallest processing time, and then by idx
- it might happen that our last job gets over at time t, but next job is at time t+10
- we update the current time using what idx of tasks has
- finally, we also add the processing time to the current time when we remove a task from the queue

```java
class Solution {

    public int[] getOrder(int[][] _tasks) {

        Task[] tasks = new Task[_tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(i, _tasks[i][0], _tasks[i][1]);
        }

        Arrays.sort(tasks, (a, b) -> a.enqueueTime - b.enqueueTime);

        PriorityQueue<Task> minHeap = new PriorityQueue<>((a, b) -> {

            if (a.processingTime != b.processingTime) {
                return a.processingTime - b.processingTime;
            }

            return a.idx - b.idx;
        });

        int[] result = new int[tasks.length];
        int count = 0;
        int idx = 0;

        int currentTime = 0;

        while (count < tasks.length) {

            if (minHeap.isEmpty()) {

                int enqueueTime = tasks[idx].enqueueTime;

                while (idx < tasks.length && tasks[idx].enqueueTime == enqueueTime) {
                    minHeap.add(tasks[idx]);
                    idx += 1;
                }

                currentTime = enqueueTime;
            }

            Task task = minHeap.remove();

            result[count] = task.idx;
            count += 1;

            currentTime += task.processingTime;

            while (idx < tasks.length && tasks[idx].enqueueTime <= currentTime) {
                minHeap.add(tasks[idx]);
                idx += 1;
            }
        }

        return result;
    }

    static class Task {

        int idx;
        int enqueueTime;
        int processingTime;

        Task(int idx, int enqueueTime, int processingTime) {
            this.idx = idx;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }

        @Override
        public String toString() {
            return "(" + idx + ", " + enqueueTime + ", " + processingTime + ")";
        }
    }
}
```
