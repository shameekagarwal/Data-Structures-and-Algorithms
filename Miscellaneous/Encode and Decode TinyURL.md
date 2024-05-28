# Encode and Decode TinyURL

- https://leetcode.com/problems/encode-and-decode-tinyurl/

```java
public class Codec {

    private List<String> urls = new ArrayList<>();
    private static final List<Character> map;
    private static final Map<Character, Integer> mapLookup;

    static {

        map = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++) {
            map.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            map.add(c);
        }

        for (char c = '0'; c <= '9'; c++) {
            map.add(c);
        }

        mapLookup = new HashMap<>();

        for (int i = 0; i < map.size(); i++) {
            mapLookup.put(map.get(i), i);
        }
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {

        urls.add(longUrl);

        int id = urls.size();

        StringBuilder tiny = new StringBuilder();

        while (id != 0) {

            char append = map.get(id % map.size());
            tiny.append(append);
            id /= map.size();
        }

        tiny.reverse();

        return tiny.toString();
        // 17 - 11
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {

        char[] array = shortUrl.toCharArray();
        int size = 0;

        for (int i = 0; i < array.length; i++) {
            int idx = mapLookup.get(array[i]);
            size = (size * map.size()) + idx;
        }

        return urls.get(size - 1);
    }
}
```
