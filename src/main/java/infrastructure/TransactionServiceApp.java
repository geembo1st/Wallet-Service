package infrastructure;
import domain.Player;
import domain.TransactionHistory;
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
    public boolean debit(Player player, long amount, String transactionId) throws Exception {
        TransactionHistory transaction = new TransactionHistory(transactionId,player);
        if(playerRepository.getBalance(player.getId())>=amount) {
            Configuration configuration = new Configuration().addAnnotatedClass(Player.class)
                    .addAnnotatedClass(TransactionHistory.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();
                if(!player.getTransactionsName(player.getId()).
                        stream().anyMatch(transaction11 -> transaction11.getTransaction_name().equals(transactionId))){
            try {
                session.beginTransaction();
                player = session.get(Player.class, player.getId());
                player.setBalance(player.getBalance()-amount);
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
                player.addTransactionToHistory(transaction);
                session.getTransaction().commit();
            } finally {
                sessionFactory.close();
            }
            } else {
                auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
                    sessionFactory.close();
                    throw new TransactionException("Номер транзакции существует");
            }
        } else {
            auditService.logAction(player.getUsername()," transaction: " + transactionId,false);
            throw new TransactionException("Недостаточно средств для перевода");
        }
        return true;
    }
    public boolean credit(Player player, long amount, String transactionId) {
           TransactionHistory transaction = new TransactionHistory(transactionId,player);
            if(amount >0) {
                Configuration configuration = new Configuration().addAnnotatedClass(Player.class)
                        .addAnnotatedClass(TransactionHistory.class);
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.getCurrentSession();
                if(!player.getTransactionsName(player.getId()).
                        stream().anyMatch(transaction11 -> transaction11.getTransaction_name().equals(transactionId))){
                    try {
                        session.beginTransaction();
                        player = session.get(Player.class, player.getId());
                        player.setBalance(player.getBalance()+amount);
                        player.addTransactionToHistory(transaction);
                        auditService.logAction(player.getUsername()," транзакция: " + transactionId,true);
                        session.getTransaction().commit();
                    } finally {
                        sessionFactory.close();
                    }
                } else {
                    auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
                    sessionFactory.close();
                    throw new TransactionException("Номер транзакции существует");
                }
            } else {
            auditService.logAction(player.getUsername()," транзакция: " + transactionId,false);
            throw new TransactionException("Введите ссумму больше 0");
        }
        return true;
    }
}
