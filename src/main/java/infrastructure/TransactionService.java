package infrastructure;
import domen.Player;
public interface TransactionService {
    boolean debit(Player player, int amount, String transactionId);
    boolean credit(Player player, int amount, String transactionId);
}
