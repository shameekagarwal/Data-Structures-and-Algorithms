# Rising Temperature

- https://leetcode.com/problems/rising-temperature/
- use self join
- join condition is the date difference
  - important - look at the date add function - might not be handy in interviews
- where condition is the higher temperature

```sql
select current_day_weather.id
from weather current_day_weather
join weather previous_day_weather
on date_add(current_day_weather.recordDate, interval -1 day) = previous_day_weather.recordDate
where current_day_weather.temperature > previous_day_weather.temperature
```
