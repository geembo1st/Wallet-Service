package infrastructure;

import domen.Player;

public class TransactionServiceApp implements TransactionService {
    private PlayerRepository playerRepository;
    private AuditService auditService;

    public TransactionServiceApp(PlayerRepository playerRepository, AuditService auditService) {
        this.playerRepository=playerRepository;
        this.auditService=auditService;
    }
    public void debit(Player player, long amount, String transactionId) {
        if(player.getBalance()>=amount) {
            if(!player.getTransactionHistory().contains(transactionId)){
                player.setBalance(player.getBalance()-amount);
                player.addTransactionToHistory(transactionId);
                System.out.println("Транзакция произведена");
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
            } else {
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
                throw new RuntimeException("Номер транзакции существует");
            }
        } else {
            auditService.logAction(player.getUsername()," transaction: " + transactionId,false);
            throw new RuntimeException("Транзакция не может быть произведена");
        }
    }
    public void credit(Player player, long amount, String transactionId) {
        if(amount >0) {
            if(!(player.getTransactionHistory().contains(transactionId)) || player.getTransactionHistory().isEmpty()){
                player.setBalance(player.getBalance()+amount);
                player.addTransactionToHistory(transactionId);
                System.out.println("Транзакция произведена");
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
            } else {
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
                throw new RuntimeException("Номер транзакции существует");
            }
        } else {
            auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
            throw new RuntimeException("Транзакция не может быть произведена");
        }
    }
}
