package Domen;

/**
 * The type Admin.
 */
public class Admin extends User {
    public Admin() {
        this.username = "admin";
        this.password = "admin".toCharArray();
        this.isAdmin = true;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
