package infrastructure;
import domen.Player;
import domen.TransactionHistory;
import exception.TransactionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The type Transaction service app.
 */
public class TransactionServiceApp implements TransactionService {
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
    public boolean debit(Player player, int amount, String transactionId) {
        TransactionHistory transaction = new TransactionHistory(transactionId);
        if(player.getBalance()>=amount) {
            Configuration configuration = new Configuration().addAnnotatedClass(Player.class)
                    .addAnnotatedClass(TransactionHistory.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            if(!player.getTransactionsName().contains(transaction.getTransaction_name())){
            try {
                session.beginTransaction();
                Player playerAfterTransaction = session.get(Player.class, player.getId());
                playerAfterTransaction.setBalance(player.getBalance()-amount);
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
                player.addTransactionToHistory(transaction);
            } finally {
                sessionFactory.close();
            }
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
    public boolean credit(Player player, int amount, String transactionId) {
            TransactionHistory transaction = new TransactionHistory(transactionId);
            if(amount >0) {
                Configuration configuration = new Configuration().addAnnotatedClass(Player.class)
                        .addAnnotatedClass(TransactionHistory.class);
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.getCurrentSession();
                if(!player.getTransactionsName().contains(transaction.getTransaction_name())){
                    try {
                        session.beginTransaction();
                        Player playerAfterTransaction = session.get(Player.class, player.getId());
                        playerAfterTransaction.setBalance(player.getBalance()+amount);
                        auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
                        player.addTransactionToHistory(transaction);
                    } finally {
                        sessionFactory.close();
                    }
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
