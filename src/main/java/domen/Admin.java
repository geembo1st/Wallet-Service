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


    @Override
    public String getUsername() {
        return super.getUsername();
    }
}
