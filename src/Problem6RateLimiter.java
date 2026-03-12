import java.util.HashMap;

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefillTime;

    public TokenBucket(int maxTokens) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public boolean allowRequest() {
        long now = System.currentTimeMillis();

        if (now - lastRefillTime > 3600000) { // 1 hour
            tokens = maxTokens;
            lastRefillTime = now;
        }

        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}

public class Problem6RateLimiter {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    public static void checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(1000));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.allowRequest()) {
            System.out.println("Allowed. Remaining: " + bucket.tokens);
        } else {
            System.out.println("Denied. Limit exceeded.");
        }
    }

    public static void main(String[] args) {

        checkRateLimit("abc123");
        checkRateLimit("abc123");
        checkRateLimit("abc123");

    }
}