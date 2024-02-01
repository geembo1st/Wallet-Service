package infrastructure;
import domain.Player;

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
    boolean debit(Player player, long amount, String transactionId) throws Exception;

    /**
     * Credit boolean.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @return the boolean
     */
    boolean credit(Player player, long amount, String transactionId);
}
