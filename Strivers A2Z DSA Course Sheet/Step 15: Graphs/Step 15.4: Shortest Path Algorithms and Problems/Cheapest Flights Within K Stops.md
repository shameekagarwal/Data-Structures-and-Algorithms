# Cheapest Flights Within K Stops

- https://leetcode.com/problems/cheapest-flights-within-k-stops/
- we just use a normal bfs
- another note - do not stop once we find a non infinite value for destination - it might happen that a longer path that is still less than k yields a shorter distance, because this is a plain bfs
- remember how we saw in [dijkstra](./Dijkstra's%20Shortest%20Path.md) queue would work, but it would increase the complexity? we use that here
- i break out of loop once i see the first route that already has k stops - because this point onwards, routes will start having more than k stops - remember this is normal bfs
- another mistake i was stuck on - do not do `distance[currentNode] + weight` when updating answer like we can in dijkstra here - basically, `distance[currentNode]` need not be same as distance that is part of popped node - remember - we have an additional parameter k. so, we would have to do current element popped form queue's distance + weight
- distance of reaching the current route's node can be lesser than current route's distance
- basically, do `route.price`, not `prices[route.destination]`
- time complexity - quadratic - O(n * k)?

```java
class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        /* construct adjacency list */
        
        List<List<Flight>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            graph.get(flight[0]).add(new Flight(flight[1], flight[2]));
        }



        /* perform dijkstra */

        int[] prices = new int[n];
        Arrays.fill(prices, -1);
        Deque<Route> queue = new ArrayDeque<>();
        
        queue.addLast(new Route(src, 0, 0));
        prices[src] = 0;

        while (!queue.isEmpty()) {

            Route route = queue.removeFirst();

            // routes will now start exceeding k
            if (route.stops == k + 1) break;

            for (Flight flight : graph.get(route.destination)) {
                if (prices[flight.destination] == -1 || prices[flight.destination] > route.price + flight.price) {
                    prices[flight.destination] = route.price + flight.price;
                    queue.addLast(new Route(flight.destination, route.stops + 1, prices[flight.destination]));
                }
            }

            // System.out.println(queue);
            // System.out.println(Arrays.toString(prices));
        }

        return prices[dst];
    }

    static class Flight {

        int destination;
        int price;

        Flight(int destination, int price) {
            this.destination = destination;
            this.price = price;
        }
        
        @Override
        public String toString() {
            return "Flight(destination=" + destination + ", price=" + price + ")";
        }
    }

    static class Route extends Flight {

        int stops;

        Route(int destination, int stops, int price) {
            super(destination, price);
            this.stops = stops;
        }

        @Override
        public String toString() {
            return "Route(" + super.toString() + ", stops=" + stops + ")";
        }
    }
}
```
