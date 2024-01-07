package domen;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "Transaction_history")
public class Transaction_history {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_name")
    private String transaction_name;
    @ManyToOne
    @JoinColumn(name = "player_id",referencedColumnName = "id")
    private Player owner;
    public Transaction_history(){}
    public Transaction_history(String transaction_name) {
        this.transaction_name = transaction_name;
    }
    @Override
    public String toString() {
        return "Transaction_history{" +
                "id=" + id +
                ", transaction_name='" + transaction_name + '\'' +
                '}';
    }
}
