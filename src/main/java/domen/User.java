package domen;

public abstract class User {
    protected String username;
    protected char[] password;
    protected boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

}
