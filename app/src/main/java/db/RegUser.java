package db;

public class RegUser {

    private String email,pass;

    public RegUser(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public RegUser(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
