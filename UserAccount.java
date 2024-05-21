public class UserAccount extends User {
    private String email;
    private String password;
    private int redeemablePoints;

    // Constructor
    public UserAccount(int userID, String username, String contactInfo, String email, String password, int redeemablePoints) {
        super(userID, username, contactInfo);
        this.email = email;
        this.password = password;
        this.redeemablePoints = redeemablePoints;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for redeemablePoints
    public int getRedeemablePoints() {
        return redeemablePoints;
    }

    public void setRedeemablePoints(int redeemablePoints) {
        this.redeemablePoints = redeemablePoints;
    }

    // Getter for accountID (same as userID)
    public int getAccountID() {
        return getUserID();
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "UserAccount{" +
                "accountID=" + getAccountID() +
                ", username='" + getUsername() + '\'' +
                ", contactInfo='" + getContactInfo() + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", redeemablePoints=" + redeemablePoints +
                '}';
    }
}

