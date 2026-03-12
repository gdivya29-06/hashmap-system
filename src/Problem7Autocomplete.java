import java.util.*;

public class Problem7Autocomplete {

    static HashMap<String, Integer> queries = new HashMap<>();

    public static void addQuery(String q, int freq) {
        queries.put(q, freq);
    }

    public static void search(String prefix) {

        System.out.println("Suggestions:");

        queries.entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith(prefix))
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e ->
                        System.out.println(e.getKey() + " (" + e.getValue() + ")"));
    }

    public static void main(String[] args) {

        addQuery("java tutorial", 1200);
        addQuery("javascript guide", 900);
        addQuery("java download", 600);

        search("jav");
    }
}
