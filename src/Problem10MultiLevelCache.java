import java.util.LinkedHashMap;
import java.util.HashMap;

class L1Cache extends LinkedHashMap<String, String> {

    private int capacity;

    public L1Cache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(java.util.Map.Entry<String,String> eldest) {
        return size() > capacity;
    }
}

public class Problem10MultiLevelCache {

    static L1Cache L1 = new L1Cache(3);
    static HashMap<String,String> L2 = new HashMap<>();
    static HashMap<String,String> DB = new HashMap<>();

    public static String getVideo(String id) {

        if (L1.containsKey(id)) {
            System.out.println("L1 Cache HIT");
            return L1.get(id);
        }

        if (L2.containsKey(id)) {
            System.out.println("L2 Cache HIT");
            L1.put(id, L2.get(id));
            return L2.get(id);
        }

        if (DB.containsKey(id)) {
            System.out.println("DB HIT");
            L2.put(id, DB.get(id));
            return DB.get(id);
        }

        return "Video not found";
    }

    public static void main(String[] args) {

        DB.put("video1", "Movie A");
        DB.put("video2", "Movie B");

        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video1"));

    }
}