package Workshop2;

public class User {

    private  int id;
    private String email;
    private String username;
    private String password;

    public User() {}

    public User(int id, String email, String username, String password) {
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
