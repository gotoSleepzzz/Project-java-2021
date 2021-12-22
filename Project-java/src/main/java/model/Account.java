package model;

public class Account {
    private String username;
    private String pass;
    private String role;
    private boolean status;

    public Account(String username, String pass, String role, boolean status) {
        this.username = username;
        this.pass = pass;
        this.role = role;
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean status() {
        return status;
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

    public boolean getStatus() {
        return status;
    }
}
