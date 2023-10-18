package infrastructure;


import domen.Player;

public interface TransactionService {
    void debit(Player player, long amount, String transactionId);

    void credit(Player player, long amount, String transactionId);
}
