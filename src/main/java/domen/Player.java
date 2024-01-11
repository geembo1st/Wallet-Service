package domen;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private char[] password;
    @Column(name = "balance")
    private int balance;

    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE,
    org.hibernate.annotations.CascadeType.SAVE_UPDATE})


    private List<TransactionHistory> transactions;


    public List<String> getTransactionsName() {
        List<String> transactionsName;
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            transactionsName = session.createQuery("SELECT transaction_name FROM Transaction_history", String.class).getResultList();
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
        return transactionsName;
    }

    public Player(String username, char[] password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Player(){}

    public void addTransactionToHistory(TransactionHistory transaction) {
////        if(this.transactions == null) {
////            this.transactions = new ArrayList<>();
////            this.transactions.add(transaction);
////            transaction.setOwner(this);
//        }
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
                ", password=" + Arrays.toString(password) +
                ", balance=" + balance +
                '}';
    }
}
