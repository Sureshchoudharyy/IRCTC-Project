public class User {
    private String username;
    private String password;
    private String fullName;
    private String phoneNo;

    public User(String username, String password, String fullName, String phoneNo) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return fullName+" ("+username+")";
    }
}
