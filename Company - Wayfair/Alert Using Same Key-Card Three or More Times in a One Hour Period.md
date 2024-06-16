# Alert Using Same Key-Card Three or More Times in a One Hour Period

- https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
- map{string, list{access_time}}
- sort all the access times for a user
- check all windows of size 3 - if start and end of these window is within 60 minutes, the user would have been alerted
- how to check the difference - hours * 60 + minutes to get the minutes, and then subtract the two 

```java
class Solution {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        
        Map<String, List<String>> nameAccessLookup = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {

            if (!nameAccessLookup.containsKey(keyName[i])) {
                nameAccessLookup.put(keyName[i], new ArrayList<>());
            }

            nameAccessLookup.get(keyName[i]).add(keyTime[i]);
        }

        for (List<String> accesses : nameAccessLookup.values()) {
            Collections.sort(accesses);
        }

        List<String> alertedUsers = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : nameAccessLookup.entrySet()) {

            List<String> accesses = entry.getValue();
            String user = entry.getKey();

            for (int i = 0; i < accesses.size() - 2; i++) {
                
                int difference = minutesDifference(accesses.get(i), accesses.get(i + 2));
                
                if (difference <= 60) {
                    alertedUsers.add(user);
                    break;
                }
            }
        }

        Collections.sort(alertedUsers);

        return alertedUsers;
    }

    private int minutesDifference(String a, String b) {

        int minutesA = Integer.parseInt(a.split(":")[0]) * 60 + Integer.parseInt(a.split(":")[1]);
        int minutesB = Integer.parseInt(b.split(":")[0]) * 60 + Integer.parseInt(b.split(":")[1]);

        return minutesB - minutesA;
    }
}
```
