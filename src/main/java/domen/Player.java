package domen;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;

/**
 * The type Player.
 */
@Getter
public class Player extends User {
    @Setter private int balance;
    private HashMap<String,Integer> transactionHistory = new HashMap<>();

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
     * Add transaction to history.
     *
     * @param transactionId the transaction id
     * @param amount        the amount
     */
    public void addTransactionToHistory(String transactionId,Integer amount) {
        transactionHistory.put(transactionId,amount);
    }
}
