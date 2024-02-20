# Design Twitter

- https://leetcode.com/problems/design-twitter/
- follow / unfollow efficiently - `map<int, set<int>>`
- tweets - inspired by [Flattening of LL](/Striver%20-%20Interview%20Prep/Step%206:%20Learn%20LinkedList/Step%206.5/Flattening%20of%20LL.md)
- model tweets by a user like a linked list
- we use `map<int, tweet>`, to track tweets by a user, where the head is the latest tweet, and as we go forward into the linked list, we go into the history of the tweets by the user
- each tweet has a timestamp / tweetNumber (incrementing id) - helps with ordering them by recency in decreasing order
- so, every time we extract a tweet from the priority queue, we add its next (if not null) to the priority queue again

```java
class Twitter {

    private static final int NEWS_FEED_LIMIT = 10;

    private Map<Integer, Set<Integer>> following;
    private Map<Integer, Tweet> tweets;
    private int tweetCount;

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
        tweetCount = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweetCount += 1;
        Tweet newTweet = new Tweet(tweetId, tweetCount);
        if (tweets.containsKey(userId)) {
            newTweet.previousTweet = tweets.get(userId);
        }
        tweets.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Tweet> newsFeedMaxHeap = new PriorityQueue<>((a, b) -> b.tweetCount - a.tweetCount);

        // add tweets by user
        if (tweets.containsKey(userId)) {
            newsFeedMaxHeap.add(tweets.get(userId));
        }

        // add tweets by followees
        if (following.containsKey(userId)) {
            for (int followee : following.get(userId)) {
                if (tweets.containsKey(followee)) {
                    newsFeedMaxHeap.add(tweets.get(followee));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (result.size() < NEWS_FEED_LIMIT && !newsFeedMaxHeap.isEmpty()) {
            Tweet tweet = newsFeedMaxHeap.remove();
            if (tweet.previousTweet != null) {
                newsFeedMaxHeap.add(tweet.previousTweet);
            }
            result.add(tweet.tweetId);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (!following.containsKey(followerId)) {
            following.put(followerId, new HashSet<>());
        }
        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);   
        }
    }
}

class Tweet {

    int tweetId;
    int tweetCount;
    Tweet previousTweet;

    Tweet(int tweetId, int tweetCount) {
        this.tweetId = tweetId;
        this.tweetCount = tweetCount;
        previousTweet = null;
    }

    @Override
    public String toString() {
        return "Tweet(tweetId: " + tweetId + ", tweetCount: " + tweetCount + ")";
    }
}
```
