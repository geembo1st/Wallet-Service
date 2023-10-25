package infrastructure;
import domen.User;

import java.util.List;

/**
 * The interface Player repository.
 */
public interface PlayerRepository {
    /**
     * Register player.
     *
     * @param user the user
     * @throws Exception the exception
     */
    void registerPlayer(User user) throws Exception;

    /**
     * Authenticate player user.
     *
     * @param userName the user name
     * @param password the password
     * @return the user
     * @throws Exception the exception
     */
    User authenticatePlayer(String userName, char [] password) throws Exception;

    /**
     * Gets players.
     *
     * @return the players
     */
    List<User> getPlayers();
}
