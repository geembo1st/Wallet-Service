package Domen;

import java.util.List;

/**
 * The type Player.
 */
public class Player extends User {
    private long balance;
    private List<String> transactionHistory;

    /**
     * Instantiates a new Player.
     *
     * @param username the username
     * @param password the password
     */
    public Player(String username, char [] password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public long getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * Sets transaction history.
     *
     * @param transactionId the transaction id
     */
    public void addTransactionToHistory(String transactionId) {
        transactionHistory.add(transactionId);
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets transaction history.
     */
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    /**
     * Get password char [ ].
     *
     * @return the char [ ]
     */
    public char[] getPassword() {
        return password;
    }
}
