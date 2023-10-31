package domen;
import lombok.Getter;
/**
 * The type User.
 */
@Getter
public abstract class User {
    /**
     * The Username.
     */
    protected String username;
    /**
     * The Password.
     */
    protected char[] password;
    /**
     * The Is admin.
     */
    protected boolean isAdmin;
    @Override
    public String toString() {
        return username + (isAdmin ? " (Admin)" : "");
    }
}
