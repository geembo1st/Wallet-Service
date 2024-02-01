package domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.cfg.Configuration;
import javax.persistence.*;
import java.util.List;

/**
 * The type Player.
 */
@Getter
@Setter
@Entity
@Table(name = "Player")
public class Player extends User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private char[] password;
    @Column(name = "balance")
    private long balance;

    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<TransactionHistory> transactions;


    public List<TransactionHistory> getTransactionsName(long id) {
        List <TransactionHistory> transactionHistories;
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Player player = session.get(Player.class, id);
            transactionHistories = player.getTransactions();
            System.out.println(transactionHistories);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            sessionFactory.close();
        }
        return transactionHistories;
    }

    public Player(String username, char[] password, long balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Player(){}

    public void addTransactionToHistory(TransactionHistory transaction) {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(transaction);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }


    @Override
    public String toString() {
        return  "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}
