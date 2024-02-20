# Program for Shortest Job First

- https://www.codingninjas.com/studio/problems/sjf_1172165

```java
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

public class Solution {

    public static float sjf(int n, int[] arrivalTimes, int[] burstTimes) {

        // form a list of jobs
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < arrivalTimes.length; i++) {
            jobs.add(new Job(arrivalTimes[i], burstTimes[i]));
        }

        // sort it by arrival time
        Collections.sort(jobs, (a, b) -> a.arrivalTime - b.arrivalTime);

        // keep track of jobs that are yet to be queued
        boolean[] isJobScheduled = new boolean[jobs.size()];

        // used to get job with least burst time
        PriorityQueue<Job> queue = new PriorityQueue<>((a, b) -> a.burstTime - b.burstTime);
        long totalWaitingTime = 0;
        int jobsIdx = 0;
        int currentTime = 0;
        
        while (jobsIdx < jobs.size() || !queue.isEmpty()) {

            while (jobsIdx < jobs.size() && currentTime >= jobs.get(jobsIdx).arrivalTime) {
                queue.add(jobs.get(jobsIdx));
                jobsIdx += 1;
            }

            // jobs arrive after current time
            // so, we could not enqueue any jobs
            // so, jump current time to arrival time of next job
            // e.g. job 1 - 1,10, job 2 - 15,5
            if (queue.isEmpty()) {
                currentTime = jobs.get(jobsIdx).arrivalTime;
                continue;
            }

            Job job = queue.poll();
            totalWaitingTime += (currentTime - job.arrivalTime);
            currentTime += job.burstTime;
        }

        return (float)((totalWaitingTime * 1.0) / jobs.size());
    }

    static class Job {

        int arrivalTime;
        int burstTime;

        Job(int arrivalTime, int burstTime) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }

        @Override
        public String toString() {
            return "Job(" + arrivalTime + ", " + burstTime + ")";
        }
    }
}
```
