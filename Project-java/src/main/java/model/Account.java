package model;

public class Account {
    private String username;
    private String pass;
    private String role;

    public Account(String username, String pass, String role) {
        this.username = username;
        this.pass = pass;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }

    public Account() {
    }
    
    
}
