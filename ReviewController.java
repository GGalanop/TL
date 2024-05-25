import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewController {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    // Method to submit a review to the database
    public void submitReview(Review review) {
        String insertSQL = "INSERT INTO Reviews (ReviewID, StarRating, Comment, Username) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setInt(1, review.getReviewID());
            preparedStatement.setInt(2, review.getStarRating());
            preparedStatement.setString(3, review.getComment());
            preparedStatement.setString(4, review.getUsername());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Review submitted successfully.");
            } else {
                System.out.println("Failed to submit review.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method for testing the ReviewController
    public static void main(String[] args) {
        Review review = new Review(1, 5, "Great service!", "john_doe");
        ReviewController controller = new ReviewController();
        controller.submitReview(review);
    }
}

