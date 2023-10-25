package infrastructure;
import domen.Player;

/**
 * The interface Transaction service.
 */
public interface TransactionService {
    /**
     * Debit boolean.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @return the boolean
     */
    boolean debit(Player player, int amount, String transactionId);

    /**
     * Credit boolean.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @return the boolean
     */
    boolean credit(Player player, int amount, String transactionId);
}
