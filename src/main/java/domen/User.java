package domen;
public abstract class User {
    protected String username;
    protected char[] password;
    protected boolean isAdmin;
    public String getUsername() {
        return username;
    }
    public char[] getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return username + (isAdmin ? " (Admin)" : "");
    }
}
