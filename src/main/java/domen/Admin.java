package domen;

/**
 * The type Admin.
 */
public class Admin extends User {
    public Admin() {
        this.username = "admin";
        this.password = "admin".toCharArray();
        this.isAdmin = true;
    }


    public String getUsername() {
        return username;
    }
}
