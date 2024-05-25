import java.util.HashMap;
import java.util.Map;

public class FAQ {
    private Map<String, String> faqList;

    // Constructor
    public FAQ() {
        faqList = new HashMap<>();
        loadFAQs();
    }

    // Method to load FAQs
    private void loadFAQs() {
        faqList.put("What are your operating hours?", "We are open all Day, all week.");
        faqList.put("Where are you located?", "We are located at Korinthou 64, Patras.");
        faqList.put("How can I reset my password?", "You can reset your password by clicking on 'Forgot Password' on the login page.");
    }

    // Method to get the answer for a given question
    public String getAnswer(String question) {
        return faqList.getOrDefault(question, "Sorry, we do not have an answer for that question.");
    }

    // Method to display all FAQs
    public void displayFAQs() {
        System.out.println("Frequently Asked Questions:");
        for (Map.Entry<String, String> entry : faqList.entrySet()) {
            System.out.println("Q: " + entry.getKey());
            System.out.println("A: " + entry.getValue());
            System.out.println();
        }
    }
}

