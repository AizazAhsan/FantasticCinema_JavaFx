package model;

public class User {

    private String userName;
    public String password;
    private UserRole userRole;

    public User(){ }

    public User(String userName, String password, UserRole userRole) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public UserRole getUserRole() { return userRole; }

    public String getPassword() {
        return password;
    }
}
