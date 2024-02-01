package infrastructureTest;

import infrastructure.AuditServiceApp;
import infrastructure.PlayerRepositoryApp;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuditServiceAppTest {
    @Spy
    AuditServiceApp auditServiceApp;
    @Mock
    private PlayerRepositoryApp playerRepositoryApp;

//    @Test
//    void logActionTest() {
//        Player player = new Player("playerUsername", "playerPassword".toCharArray());
//        playerRepositoryApp.registerPlayer(player);
//        auditServiceApp.logAction(player.getUsername(), " регистрация ", true);
//    }
}
