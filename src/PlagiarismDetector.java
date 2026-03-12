import java.util.*;

public class PlagiarismDetector {

    private HashMap<String, Set<String>> index = new HashMap<>();

    public List<String> generateNGrams(String text, int n) {

        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++)
                gram.append(words[i + j]).append(" ");

            grams.add(gram.toString().trim());
        }

        return grams;
    }

    public void addDocument(String docId, String text) {

        List<String> grams = generateNGrams(text, 3);

        for (String gram : grams) {

            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }
    }

    public void checkDocument(String docId, String text) {

        List<String> grams = generateNGrams(text, 3);
        HashMap<String, Integer> matches = new HashMap<>();

        for (String gram : grams) {

            if (index.containsKey(gram)) {

                for (String doc : index.get(gram)) {
                    matches.put(doc, matches.getOrDefault(doc, 0) + 1);
                }
            }
        }

        System.out.println("Similarity results: " + matches);
    }

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        detector.addDocument("essay1", "java programming is very powerful language");
        detector.checkDocument("essay2", "java programming is powerful and useful");
    }
}
