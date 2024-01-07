package infrastructure;
import domen.Player;
import domen.User;

import java.util.List;

/**
 * The interface Player repository.
 */
public interface PlayerRepository {
    /**
     * Register player.
     *
     * @param player the player
     * @throws Exception the exception
     */
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
    List<User> getPlayers();
}
