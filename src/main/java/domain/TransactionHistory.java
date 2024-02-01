package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "transactionHistory")

public class TransactionHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "transaction_name")
    private String transaction_name;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player owner;

    public TransactionHistory() {
    }

    public TransactionHistory(String transaction_name, Player owner) {
        this.transaction_name = transaction_name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return " transaction_name='" + transaction_name + '\'' +
                '}';
    }
}
