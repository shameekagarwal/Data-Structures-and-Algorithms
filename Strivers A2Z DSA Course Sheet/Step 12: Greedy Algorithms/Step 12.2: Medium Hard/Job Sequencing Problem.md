# Job Sequencing Problem

- greedy - sort them according to decreasing order of profit to perform the job with maximum profit first
- perform the job on the last day so that there is time for "more important" tasks earlier
- if last is occupied, keep moving back till an empty slot is found
- time complexity - O(NlogN + N*M) (for sorting + the fact that we have to keep checking for an empty slot). here, M is the maximum deadline, and N are the number of jobs - we move the job from m to 0 in search of an empty slot
- todo - apparently, we can get rid of the extra complexity around finding an empty slot using spoj's "colorful array"

```java
class Solution {

    int[] JobScheduling(Job arr[], int n) {
        
        // arr = Arrays.copyofRange(arr, 0, arr.length);
        Arrays.sort(arr,  (job1, job2) -> job2.profit - job1.profit);
        
        int maxDeadline = 0;
        for (Job job : arr) {
            maxDeadline = Math.max(job.deadline, maxDeadline);
        }
        
        int[] time = new int[maxDeadline + 1];
        Arrays.fill(time, -1);
        int jobsAccomodated = 0;
        int profitEarned = 0;

        for (Job job : arr) {
            for (int i = job.deadline; i > 0; i--) {
                if (time[i] == -1) {
                    jobsAccomodated += 1;
                    profitEarned += job.profit;
                    time[i] = job.id;
                    break;
                }
            }
        }
        
        return new int[]{jobsAccomodated, profitEarned};
    }
}
```
