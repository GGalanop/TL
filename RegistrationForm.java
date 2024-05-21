import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegistrationForm {
    private String username;
    private String contactInfo;
    private String email;
    private String password;
    private int redeemablePoints;

    // Constructor
    public RegistrationForm(String username, String contactInfo, String email, String password, int redeemablePoints) {
        this.username = username;
        this.contactInfo = contactInfo;
        this.email = email;
        this.password = password;
        this.redeemablePoints = redeemablePoints;
    }

    // Method to check email format
    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to submit data
    public void SubmitData(RegistrationController controller) {
        if (isEmailValid(email)) {
            UserAccount userAccount = new UserAccount(1, username, contactInfo, email, password, redeemablePoints);
            controller.insertData(userAccount);
        } else {
            DisplayError();
        }
    }

    // Method to display error
    public void DisplayError() {
        new ShowError();
    }

    // Inner class to show error
    private class ShowError extends ErrorDisplay {
        @Override
        protected void showError() {
            System.out.println("An error has occurred: Invalid email format.");
        }
    }
}

