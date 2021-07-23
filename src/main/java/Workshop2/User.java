package Workshop2;

public class User {

    private static int id;
    private static String email;
    private static String username;
    private static String password;

    public void user(int id, String email, String username, String password) {
        this.id = id;
        this.email= email;
        this.username = username;
        this.password = password;
    }

    public void setId(int idParam) {
        id = idParam;
    }

    public void setEmail(String emailParam) {
        email = emailParam;
    }

    public void setUsername (String usernameParam) {
        username = usernameParam;
    }

    public void setPassword (String passwordParam) {
        password = passwordParam;
    }

    public int getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
