# Accounts Merge

- https://leetcode.com/problems/accounts-merge/
- dsu of size "accounts size"
- populate email lookup - try creating a map of email lookup, account index
- if email already exists in map, union current index with the existing email index
- this way, account we now have a dsu where all emails part of the same merged account will have the same "ultimate parent"
- now, its just implementation - i first populate a `map<int, Account>` - key is the ultimate parent and from above and Account is `name, set<emails>`
- this way, i have all accounts for an index
- note - i used tree set for sorting and to dedupe same emails
- finally, i convert it to the expected output format

```java
class Solution {

    private DisjointSetUnion dsu;
    private Map<String, Integer> emailLookup;
    private Map<Integer, Account> accountLookup;
    private List<List<String>> accounts;
    private List<List<String>> mergedAccounts;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        this.accounts = accounts;
        dsu = new DisjointSetUnion(accounts.size());
        emailLookup = new HashMap<>();
        accountLookup = new HashMap<>();
        mergedAccounts = new ArrayList<>();

        populateEmailLookup();
        populateAccountLookup();
        populateMergedAccounts();

        return mergedAccounts;
    }

    private void populateMergedAccounts() {

        for (Account account : accountLookup.values()) {
            List<String> serializedAccount = new ArrayList<>();
            serializedAccount.add(account.name);
            serializedAccount.addAll(account.emails);
            mergedAccounts.add(serializedAccount);
        }
    }

    private void populateAccountLookup() {

        for (int i = 0; i < accounts.size(); i++) {

            List<String> account = accounts.get(i);
            String name = account.get(0);

            for (int j = 1; j < account.size(); j++) {
                
                String email = account.get(j);
                int accountIndex = dsu.findParent(emailLookup.get(email));

                if (!accountLookup.containsKey(accountIndex)) {
                    accountLookup.put(accountIndex, new Account(name));
                }

                accountLookup.get(accountIndex).addEmail(email);
            }
        }
    }

    private void populateEmailLookup() {

        for (int i = 0; i < accounts.size(); i++) {

            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {
                
                String email = account.get(j);
                
                if (emailLookup.containsKey(email)) {
                    dsu.union(i, emailLookup.get(email));
                } else {
                    emailLookup.put(email, i);
                }
            }
        }
    }

    static class DisjointSetUnion {

        private int[] parent;
        private int[] rank;

        DisjointSetUnion(int n) {

            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            rank = new int[n];
        }

        int findParent(int u) {
            if (u == parent[u]) {
                return u;
            }
            parent[u] = findParent(parent[u]);
            return parent[u];
        }

        void union(int u, int v) {
            
            int parentU = findParent(u);
            int parentV = findParent(v);
            
            if (parentU == parentV) return;

            if (rank[parentU] < rank[parentV]) {
                union(v, u);
            } else {
                parent[parentV] = parentU;
                if (rank[parentU] == rank[parentV]) {
                    rank[parentU] += 1;
                }
            }
        }
    }

    static class Account {

        String name;
        Set<String> emails;

        Account(String name) {
            this.name = name;
            this.emails = new TreeSet<>();
        }

        void addEmail(String email) {
            emails.add(email);
        }

        @Override
        public String toString() {
            return "Account(name=" + name + ", email=" + emails + ")";
        }
    }
}
```
