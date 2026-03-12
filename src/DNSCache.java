import java.util.*;

class DNSEntry {
    String ip;
    long expiry;

    DNSEntry(String ip, long ttlSeconds) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiry;
    }
}

public class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                return "Cache HIT → " + entry.ip;
            }
        }

        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(ip, 5));

        return "Cache MISS → " + ip;
    }

    private String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public static void main(String[] args) throws InterruptedException {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
    }
}