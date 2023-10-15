package Infrastructure;


import Domen.Player;

/**
 * The interface Transaction service.
 */
public interface TransactionService {
    /**
     * Debit.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @throws Exception the exception
     */
    void debit(Player player, long amount, String transactionId) throws Exception;

    /**
     * Credit.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @throws Exception the exception
     */
    void credit(Player player, long amount, String transactionId) throws Exception;
}
