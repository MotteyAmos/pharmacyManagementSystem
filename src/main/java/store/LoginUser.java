package store;

public class LoginUser {

    String userName;
    String userEmail;
    public static LoginUser createInstance = new LoginUser();

    private LoginUser(){}

    public static LoginUser getCreateInstance() {
        return createInstance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
