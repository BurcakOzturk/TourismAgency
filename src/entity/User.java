package entity;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;


    // Boş kurucu metot
    public User() {

    }


    // Parametreli kurucu metot
    public User(int id, String username, String pass, String role) {
        this.id = id;
        this.username = username;
        this.password = pass;
        this.role = role;
    }


    // Getter ve Setter metotları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
