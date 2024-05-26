import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentProcessingController {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    // Method to retrieve the payment amount from the terminal
    public void retrievePaymentAmount(String username, int ticketID) throws Exception {
        // Simulate retrieving the payment amount from the terminal
        double paymentAmount = 10.0; // Fixed amount for demonstration
        String paymentDetails = "Ticket ID: " + ticketID + ", Amount: $" + paymentAmount;

        // Add payment record to the database
        addPaymentRecord(paymentDetails);
        // Recognize account based on username
        boolean accountExists = recognizeAccount(username);

        if (accountExists) {
            // Process payment
            processPayment(username, paymentAmount);
        } else {
            throw new Exception("Account not recognized.");
        }
    }

    // Method to add payment record to the database
    private void addPaymentRecord(String paymentDetails) throws SQLException {
        String insertSQL = "INSERT INTO PaymentRecord (PaymentDetails) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, paymentDetails);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Payment record added successfully.");
            } else {
                System.out.println("Failed to add payment record.");
            }
        }
    }

    // Method to recognize the account based on username
    public boolean recognizeAccount(String username) {
        String querySQL = "SELECT COUNT(*) AS count FROM Users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Method to process the payment
    public void processPayment(String username, double amount) {
        System.out.println("Processing payment for " + username + ": $" + amount);
        displayReceipt(username, amount);
        activateExitGate(1);
        addPointsToAccount(username);
    }

    // Method to add points to the user's account
    private void addPointsToAccount(String username) {
        String updateSQL = "UPDATE Users SET RedeemablePoints = points + 10 WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, username);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Points added to account successfully.");
            } else {
                System.out.println("Failed to add points to account.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to display error message
    public void displayError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    // Method to activate the exit gate
    public void activateExitGate(int gateID) {
        ExitGate gate = new ExitGate(gateID);
        gate.open();
        if (gate.isOpen()) {
            System.out.println("The exit gate is open, you may exit now.");
        }
    }

    // Method to display the receipt
    public void displayReceipt(String username, double amount) {
        System.out.println("Receipt:");
        System.out.println("Username: " + username);
        System.out.println("Amount: $" + amount);
        System.out.println("Thank you for your payment.");
    }
}
