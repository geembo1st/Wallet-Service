package domen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Player extends User {
    private long balance;
 //   private List<String> transactionHistory = new ArrayList<>();
    private HashMap<String,Integer> transactionHistory = new HashMap<>();

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
    public void addTransactionToHistory(String transactionId,Integer amount) {
        transactionHistory.put(transactionId,amount);
    }
    public String getUsername() {
        return username;
    }
    public HashMap<String,Integer> getTransactionHistory() {
        return transactionHistory;
    }
    public char[] getPassword() {
        return password;
    }
}
