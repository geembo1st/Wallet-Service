package domen;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
@Getter
public class Player extends User {
    @Setter private int balance;
    private HashMap<String,Integer> transactionHistory = new HashMap<>();
    public Player(String username, char [] password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }
    public void addTransactionToHistory(String transactionId,Integer amount) {
        transactionHistory.put(transactionId,amount);
    }
}
