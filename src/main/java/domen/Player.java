package domen;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    @OneToOne(mappedBy = "player_username")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Username usernames;
    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE,
    org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Transaction_history> transactions;

    public Player(String username, char[] password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

 //   private HashMap<String, Integer> transactionHistory = new HashMap<>();
    public Player(){}

    public void addTransactionToHistory(Transaction_history transaction) {
        if(this.transactions == null) {
            this.transactions = new ArrayList<>();
            this.transactions.add(transaction);
            transaction.setOwner(this);
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
