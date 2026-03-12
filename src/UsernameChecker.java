import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        users.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            suggestions.add(username + i);
        }

        suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public String getMostAttempted() {
        String maxUser = "";
        int max = 0;

        for (String user : attempts.keySet()) {
            if (attempts.get(user) > max) {
                max = attempts.get(user);
                maxUser = user;
            }
        }
        return maxUser + " (" + max + " attempts)";
    }

    public static void main(String[] args) {
        UsernameChecker system = new UsernameChecker();

        system.registerUser("john_doe", 1);

        System.out.println(system.checkAvailability("john_doe"));
        System.out.println(system.checkAvailability("jane_smith"));
        System.out.println(system.suggestAlternatives("john_doe"));
        System.out.println(system.getMostAttempted());
    }
}