import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketIssuingController {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";
    
    // Method to check availability of parking spaces for the selected ticket type
    private boolean isSpaceAvailable(String ticketType) {
        String checkSQL = "SELECT COUNT(*) AS count FROM ParkingSpace WHERE ticketType = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(checkSQL)) {

            preparedStatement.setString(1, ticketType);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                // Assume max 10 spaces per ticket type for this example
                return count < 10;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Method to register the parking space in the database
    public boolean registerSpace(String ticketType) {
        if (isSpaceAvailable(ticketType)) {
            String location = "Default Location"; // This would typically be determined by some logic
            int spaceID = generateSpaceID(); // Simulate space ID generation
            
            String insertSQL = "INSERT INTO ParkingSpace (SpaceID, Location, ticketType) VALUES (?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

                preparedStatement.setInt(1, spaceID);
                preparedStatement.setString(2, location);
                preparedStatement.setString(3, ticketType);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Parking space registered successfully.");
                    issueTicket(spaceID, location, ticketType);
                    return true;
                } else {
                    System.out.println("Failed to register parking space.");
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Selected ticket type is full. Please choose another option.");
        }
        return false;
    }

    // Simulate space ID generation
    private int generateSpaceID() {
        return (int) (Math.random() * 1000); // Random space ID for demonstration
    }

    // Method to issue the ticket and display information
    public void issueTicket(int spaceID, String location, String ticketType) {
        EntryMessageDisplay display = new EntryMessageDisplay();
        display.displayTicketInfo(spaceID, location, ticketType);
    }
}
