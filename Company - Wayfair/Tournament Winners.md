# Tournament Winners

- https://leetcode.com/problems/tournament-winners/
- two important concepts here
  - using `with ... as ...`
  - using window functions
- very important - use `union all`, not `union`
- e.g. if a player is present as first player with score 10, and same score but as second player, then it would be deduped if we just use union instead of union all

## My Solution

- step 1 - obtain player_id, group_id, score for both first and second players individually
- step 2 - obtain player_id, group_id, total_score by performing `union all` on both tables of step 1
- step 3 - find maximum score in a group using table of step 2
  - group by group_id
  - aggregate by max of total score
- step 4 - find best players using table of step 2 having total score same as table of step 3 - we use "correlated queries" here
- step 5 - use players with minimum id only from step 4

```sql
with

first_player_scores as (
    select players.player_id, players.group_id, sum(first_score) as score
    from matches
    join players
    on matches.first_player = players.player_id
    group by players.player_id, players.group_id
),

second_player_scores as (
    select players.player_id, players.group_id, sum(second_score) as score
    from matches
    join players
    on matches.second_player = players.player_id
    group by players.player_id, players.group_id
),

player_scores as (
    select player_id, group_id, sum(score) as total_score
    from (
        select * from first_player_scores
        union all
        select * from second_player_scores
    ) player_scores
    group by player_id, group_id
),

group_max_scores as (
    select group_id, max(total_score) as max_total_score
    from player_scores
    group by group_id
),

best_players as (
    select group_id, player_id, total_score
    from player_scores
    where total_score = (
        select max_total_score
        from group_max_scores
        where group_max_scores.group_id = player_scores.group_id
    )
)

select group_id, min(player_id) as player_id
from best_players
group by group_id
```

## Better

- use of cte (common table expressions) using window functions
- helps condense steps 3 to 5 above
  - partition using group id
  - order using total score and then player id
  - find the first row
- finally, distinct is needed - window function will output one row for every row
- basically, all rows with group id x will output the best player with minimum id
- so, we use distinct on group id, player id

```sql
with

first_player_scores as (
    select players.player_id, players.group_id, sum(first_score) as score
    from matches
    join players
    on matches.first_player = players.player_id
    group by players.player_id, players.group_id
),

second_player_scores as (
    select players.player_id, players.group_id, sum(second_score) as score
    from matches
    join players
    on matches.second_player = players.player_id
    group by players.player_id, players.group_id
),

player_scores as (
    select player_id, group_id, sum(score) as total_score
    from (
        select * from first_player_scores
        union all
        select * from second_player_scores
    ) player_scores
    group by player_id, group_id
)

select distinct
    group_id,
    first_value(player_id)
    over (
        partition by group_id
        order by total_score desc, player_id asc
    ) as player_id
from player_scores
```
