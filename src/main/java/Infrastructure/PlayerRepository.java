package Infrastructure;

import Domen.Player;
import Domen.User;

import java.util.List;

/**
 * The interface Player repository.
 */
public interface PlayerRepository {
    /**
     * Register user.
     *
     * @throws Exception the exception
     */
    void registerPlayer(User user) throws Exception;

    /**
     * Authenticate user player.
     *
     * @param password   the password
     * @return the player
     * @throws Exception the exception
     */
    User authenticatePlayer(String userName, char [] password) throws Exception;

    List<User> getPlayers();
}
