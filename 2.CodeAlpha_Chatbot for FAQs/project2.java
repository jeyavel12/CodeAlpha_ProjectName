import java.util.*;

public class FAQChatbot {

    // Store FAQs
    static Map<String, String> faqs = new HashMap<>();

    public static void main(String[] args) {

        // Step 1: Collect FAQs
        faqs.put("what is java", "Java is a high-level, object-oriented programming language.");
        faqs.put("what is oops", "OOPS stands for Object Oriented Programming System.");
        faqs.put("what is inheritance", "Inheritance allows one class to acquire properties of another class.");
        faqs.put("what is polymorphism", "Polymorphism allows methods to perform different tasks based on the object.");
        faqs.put("what is encapsulation", "Encapsulation is binding data and methods together into a single unit.");

        Scanner sc = new Scanner(System.in);

        System.out.println("🤖 FAQ Chatbot (Type 'exit' to quit)");

        while (true) {
            System.out.print("\nYou: ");
            String userQuestion = sc.nextLine();

            if (userQuestion.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Thank you! Goodbye 👋");
                break;
            }

            String response = getBestAnswer(userQuestion);
            System.out.println("Chatbot: " + response);
        }
        sc.close();
    }

    // Step 2,3,4: Preprocess, match & respond
    private static String getBestAnswer(String userQuestion) {

        double maxSimilarity = 0.0;
        String bestAnswer = "Sorry, I could not understand your question.";

        for (String faqQuestion : faqs.keySet()) {

            double similarity = cosineSimilarity(
                    preprocess(userQuestion),
                    preprocess(faqQuestion)
            );

            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                bestAnswer = faqs.get(faqQuestion);
            }
        }
        return bestAnswer;
    }

    // Step 2: Text preprocessing
    private static List<String> preprocess(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("[^a-z ]", "");
        return Arrays.asList(text.split("\\s+"));
    }

    // Step 3: Cosine Similarity
    private static double cosineSimilarity(List<String> text1, List<String> text2) {

        Set<String> vocabulary = new HashSet<>();
        vocabulary.addAll(text1);
        vocabulary.addAll(text2);

        Map<String, Integer> freq1 = new HashMap<>();
        Map<String, Integer> freq2 = new HashMap<>();

        for (String word : vocabulary) {
            freq1.put(word, Collections.frequency(text1, word));
            freq2.put(word, Collections.frequency(text2, word));
        }

        int dotProduct = 0;
        double magnitude1 = 0, magnitude2 = 0;

        for (String word : vocabulary) {
            dotProduct += freq1.get(word) * freq2.get(word);
            magnitude1 += Math.pow(freq1.get(word), 2);
            magnitude2 += Math.pow(freq2.get(word), 2);
        }

        if (magnitude1 == 0 || magnitude2 == 0)
            return 0;

        return dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
    }
}