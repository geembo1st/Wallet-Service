package infrastructure;
import domen.Player;
public interface TransactionService {
    boolean debit(Player player, long amount, String transactionId);
    boolean credit(Player player, long amount, String transactionId);
}
