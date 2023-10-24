package infrastructure;
import domen.User;

import java.util.List;
public interface PlayerRepository {
    void registerPlayer(User user) throws Exception;
    User authenticatePlayer(String userName, char [] password) throws Exception;
    List<User> getPlayers();
}
