package infrastructure;
import domen.Player;
import exception.TransactionException;
public class TransactionServiceApp implements TransactionService {
    private PlayerRepository playerRepository;
    private AuditService auditService;
    public TransactionServiceApp(PlayerRepository playerRepository, AuditService auditService) {
        this.playerRepository=playerRepository;
        this.auditService=auditService;
    }
    public boolean debit(Player player, long amount, String transactionId) {
        if(player.getBalance()>=amount) {
            if(!player.getTransactionHistory().contains(transactionId)){
                player.setBalance(player.getBalance()-amount);
                player.addTransactionToHistory(transactionId);
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
            } else {
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
                throw new TransactionException("Номер транзакции существует");
            }
        } else {
            auditService.logAction(player.getUsername()," transaction: " + transactionId,false);
            throw new TransactionException("Недостаточно средств для перевода");
        }
        return true;
    }
    public boolean credit(Player player, long amount, String transactionId) {
        if(amount >0) {
            if(!player.getTransactionHistory().contains(transactionId)){
                player.setBalance(player.getBalance()+amount);
                player.addTransactionToHistory(transactionId);
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
            } else {
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
                throw new TransactionException("Номер транзакции существует");
            }
        } else {
            auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
            throw new TransactionException("Введите ссумму больше 0");
        }
        return true;
    }
}
