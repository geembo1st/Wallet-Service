package infrastructureTest;
import domain.Player;
import exception.TransactionException;
import infrastructure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
    class TransactionServiceAppTest {
    @InjectMocks
    private TransactionServiceApp transactionServiceApp;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private AuditService auditService;
    private static Player player;
    @BeforeEach
    void prepaire() {
    player = new Player();}
    @Test
     void debitShouldReturnTrue() throws Exception {
        player.setBalance(100);
        transactionServiceApp.debit(player,70,"firstTransaction");
        int balance = (int) player.getBalance();
        Assertions.assertEquals(30,balance);
    }
    @Test
    void debitTransactionException() {
        assertAll(
                () -> assertThrows(TransactionException.class,()-> {
                    player.setBalance(100);
                    transactionServiceApp.debit(player, 70, "firstTransaction");
                    transactionServiceApp.debit(player,10,"firstTransaction");}),
                () -> assertThrows(TransactionException.class,()-> {
                    player.setBalance(100);
                    transactionServiceApp.debit(player, 110, "firstTransaction");}));
    }

    @Test
    void creditShouldReturnTrue() {
        transactionServiceApp.credit(player,70,"firstTransaction");
        int balance = (int) player.getBalance();
        Assertions.assertEquals(70,balance);
    }
    @Test
    void creditTransactionException() {
        assertAll(
                () -> assertThrows(TransactionException.class,()-> {
                    transactionServiceApp.credit(player, 70, "firstTransaction");
                    transactionServiceApp.credit(player,10,"firstTransaction");}),
                () -> assertThrows(TransactionException.class,()-> {
                    transactionServiceApp.credit(player, -10, "firstTransaction");}));
    }
}
