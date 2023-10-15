package domen;

import java.util.List;

/**
 * The type Player.
 */
public class Player extends User {
    private long balance;
    private List<String> transactionHistory;


    public Player(String username, char [] password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    public long getBalance() {
        return balance;
    }


    public void setBalance(long balance) {
        this.balance = balance;
    }


    public void addTransactionToHistory(String transactionId) {
        transactionHistory.add(transactionId);
    }


    public String getUsername() {
        return username;
    }


    public List<String> getTransactionHistory() {
        return transactionHistory;
    }


    public char[] getPassword() {
        return password;
    }
}