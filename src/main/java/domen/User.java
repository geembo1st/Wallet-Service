package domen;
import lombok.Getter;
@Getter
public abstract class User {
    protected String username;
    protected char[] password;
    protected boolean isAdmin;
    @Override
    public String toString() {
        return username + (isAdmin ? " (Admin)" : "");
    }
}
