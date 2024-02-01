package infrastructureTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerRepositoryAppTest {
//    @InjectMocks
//    private PlayerRepositoryApp playerRepositoryApp;
//    @Mock
//    private AuditService auditService;
//    @Mock
//    private Admin admin;
//    private static Player player;
//    @BeforeEach
//    void prepaire() {
//        player = new Player("playerUsername", "playerPassword".toCharArray());}
//    @Test
//    void registerPlayerTest() {
//        playerRepositoryApp.registerPlayer(player);
//    }
//    @Test
//    void registerPlayerException() {
//        assertAll(
//                () -> assertThrows(RegisterException.class,()-> {
//                    Player player2 = new Player("root", "root".toCharArray());
//                    playerRepositoryApp.registerPlayer(player2);}),
//                () -> assertThrows(RegisterException.class,()-> {
//                    playerRepositoryApp.registerPlayer(player);
//                    playerRepositoryApp.registerPlayer(player);}));
//    }
//    @Test
//    void authenticatePlayerTest() {
//        playerRepositoryApp.registerPlayer(player);
//        playerRepositoryApp.authenticatePlayer(player.getUsername(), player.getPassword());
//    }
//    @Test
//    void authenticatePlayerException() {
//                assertThrows(AuthenticateException.class,()->
//                        playerRepositoryApp.authenticatePlayer(player.getUsername(), player.getPassword()));}
}
