# Combine Two Tables

- https://leetcode.com/problems/combine-two-tables/
- question requires left outer join

```sql
select person.firstName, person.lastName, address.city, address.state
from person
left outer join address
on person.personId = address.personId
```
