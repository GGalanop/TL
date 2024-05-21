public class User {
    private int userID;
    private String username;
    private String contactInfo;

    // Constructor
    public User(int userID, String username, String contactInfo) {
        this.userID = userID;
        this.username = username;
        this.contactInfo = contactInfo;
    }

    // Getter and Setter for userID
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for contactInfo
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
