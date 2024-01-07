package domen;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "Username")
public class Username {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "player_id",referencedColumnName = "id")
    private Player player_username;
    @Column(name = "username")
    private String username;

    public Username(){};

    public Username(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Username{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
