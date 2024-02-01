package domain;

/**
 * The type Admin.
 */
public class Admin extends User {
    /**
     * Instantiates a new Admin.
     */
    public Admin() {
        this.username = "admin";
        this.password = "admin1234".toCharArray();
        this.isAdmin = true;
    }
    @Override
    public String getUsername() {
        return super.getUsername();
    }
}
