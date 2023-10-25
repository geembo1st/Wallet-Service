package infrastructureTest;
import domen.Player;
import infrastructure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
    class TransactionServiceAppTest {
    @InjectMocks
    private TransactionServiceApp transactionServiceApp;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private AuditService auditService;
    private static Player player = new Player("root", "root".toCharArray());
    @Test
     void debitShouldReturnTrue() {
        player.setBalance(100);
        String transactionId = "111";
        transactionServiceApp.debit(player,70,transactionId);
        int balance = (int) player.getBalance();
        Assertions.assertEquals(30,balance);
        transactionServiceApp.debit(player,10,"223");
        Assertions.assertEquals(20,player.getBalance());
    }
}
