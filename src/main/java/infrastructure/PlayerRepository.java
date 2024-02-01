package infrastructure;
import domain.Player;
import domain.User;

import java.util.List;

/**
 * The interface Player repository.
 */
public interface PlayerRepository {

    void registerPlayer(Player player) throws Exception;

    /**
     * Authenticate player user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     * @throws Exception the exception
     */
    User authenticatePlayer(String username, char [] password) throws Exception;

    /**
     * Gets players.
     *
     * @return the players
     */
    List<String> getPlayers();

    void deletePlayer(Player player) throws Exception;
    User nameChange(Player player,String newUsername) throws Exception;

    long getBalance(long playerId) throws Exception;
}
