package infrastructure;

import domen.Player;

import java.util.Scanner;

/**
 * The type Transaction service app.
 */
public class TransactionServiceApp implements TransactionService {
    /**
     * The Scanner.
     */
    Scanner scanner = new Scanner(System.in);
    private PlayerRepository playerRepository;
    private AuditService auditService;

    /**
     * Instantiates a new Transaction service app.
     *
     * @param playerRepository the player repository
     * @param auditService     the audit service
     */
    public TransactionServiceApp(PlayerRepository playerRepository, AuditService auditService) {
        this.playerRepository=playerRepository;
        this.auditService=auditService;
    }
    /**
     * Debit.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @throws Exception the exception
     */
    public void debit(Player player, long amount, String transactionId) {
        if(player.getBalance() >= 0 && player.getBalance()>=amount) {
            System.out.println("Введите номер транзакции ");
            String transaction = scanner.nextLine();  //toDo подумать насчет уникальности transactionId
            if(transaction.equals(transactionId)){    //toDo транзакции должны храниться где-то, в коллекции например? чтобы из использовать с помощью идентификатора?
                player.setBalance(player.getBalance()-amount);
                player.addTransactionToHistory(transactionId);
                boolean success1 = true;
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,success1);
            } else {
                boolean success2 = false;
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,success2);
                throw new RuntimeException("Номер транзакции сужествует");
            }
        } else {
            boolean success3 = false;
            auditService.logAction(player.getUsername()," transaction: " + transactionId,success3);
            throw new RuntimeException("Транзакция не может быть произведена");
        }
    }
    /**
     * Credit.
     *
     * @param player        the player
     * @param amount        the amount
     * @param transactionId the transaction id
     * @throws Exception the exception
     */
    public void credit(Player player, long amount, String transactionId) {
        if(player.getBalance() >= 0 && player.getBalance()>=amount) {
            System.out.println("Введите номер транзакции ");
            String transaction = scanner.nextLine(); //toDo подумать насчет уникальности transactionId
            if(transaction.equals(transactionId)){
                player.setBalance(player.getBalance()+amount);
                boolean success1 = true;
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,success1);
            } else {
                boolean success2 = false;
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,success2);
                throw new RuntimeException("Номер транзакции сужествует");
            }
        } else {
            boolean success3 = false;
            auditService.logAction(player.getUsername()," транзакция: " + transactionId,success3);
            throw new RuntimeException("Транзакция не может быть произведена");
        }
    }
}
