import java.util.HashMap;

public class Problem9TwoSumTransactions {

    public static void findTwoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            int complement = target - num;

            if (map.containsKey(complement)) {
                System.out.println("Pair Found: " + num + " + " + complement);
                return;
            }

            map.put(num, 1);
        }

        System.out.println("No pair found");
    }

    public static void main(String[] args) {

        int[] transactions = {500, 300, 200};

        findTwoSum(transactions, 500);
    }
}
