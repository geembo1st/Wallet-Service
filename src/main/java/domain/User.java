package domain;
import lombok.Getter;
import lombok.Setter;

/**
 * The type User.
 */
@Getter
@Setter
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
