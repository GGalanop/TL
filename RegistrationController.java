import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationController {
    
    // Database URL, username and password
    private static final String DB_URL = "jdbc:mysql://${MYSQL_HOST:localhost}:3306/tl";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to insert data into the database
    public void insertData(UserAccount userAccount) {
        String insertSQL = "INSERT INTO users (userID, username, contactInfo, email, password, redeemablePoints) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            
            preparedStatement.setInt(1, userAccount.getAccountID());
            preparedStatement.setString(2, userAccount.getUsername());
            preparedStatement.setString(3, userAccount.getContactInfo());
            preparedStatement.setString(4, userAccount.getEmail());
            preparedStatement.setString(5, userAccount.getPassword());
            preparedStatement.setInt(6, userAccount.getRedeemablePoints());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                displayConfirmation();
            } else {
                System.out.println("An error occurred while inserting data into the database.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to display confirmation message
    public void displayConfirmation() {
        System.out.println("Registration successful! Data has been saved to the database.");
    }

    // Method to process registration form
    public void processRegistration(RegistrationForm form) {
        form.SubmitData(this);
    }

    // Main method for testing the controller
    public static void main(String[] args) {
        RegistrationController controller = new RegistrationController();

        // Create a registration form with valid email
        RegistrationForm validForm = new RegistrationForm("JohnDoe", "123-456-7890", "johndoe@example.com", "password123", 100);
        controller.processRegistration(validForm);

        // Create a registration form with invalid email
        RegistrationForm invalidForm = new RegistrationForm("JaneDoe", "123-456-7890", "janedoe@example", "password456", 200);
        controller.processRegistration(invalidForm);
    }
}

