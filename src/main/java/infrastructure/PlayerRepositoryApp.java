package infrastructure;
import domen.Admin;
import domen.User;
import exception.AuthenticateException;
import exception.RegisterException;
import lombok.Setter;

import java.util.*;

/**
 * The type Player repository app.
 */
public class PlayerRepositoryApp implements PlayerRepository {
    private Admin admin;
    private Map<String, User> playerMap;
    private AuditService auditService;

    /**
     * Instantiates a new Player repository app.
     *
     * @param auditService the audit service
     * @param admin        the admin
     */
    public PlayerRepositoryApp(AuditService auditService, Admin admin) {
        this.admin=admin;
        this.playerMap = new HashMap<>();
        this.playerMap.put(admin.getUsername(), admin);
        this.auditService = auditService;
    }
    public void registerPlayer(User user)  {
        if (user.getPassword().length < 6) {
            throw new RegisterException("Пароль должен содержать не менее 6 символов");
        }
        if (playerMap.containsKey(user.getUsername())) {
            auditService.logAction(user.getUsername()," регистрация ", false);
            throw new RegisterException("Пользователь с таким логином уже существует");
        } else {
            auditService.logAction(user.getUsername(), " регистрация ", true);
            this.playerMap.put(user.getUsername(), user);
            System.out.println("Регистрация прошла успешно");
        }
    }
    public User authenticatePlayer(String userName, char [] password) {
        User user = playerMap.get(userName);
        if (user != null && String.valueOf(user.getPassword()).equals(String.valueOf(password)) &&
                userName.equals(user.getUsername())) {
            System.out.println("Аутентификация пользователя прошла успешно: " + userName);
            auditService.logAction(user.getUsername()," аутентификация ", true);
            return user;
        } else {
            auditService.logAction(userName," аутентификация ", false);
            throw new AuthenticateException("Аутентификация не удалась, не найден пользователь");
        }
    }
    @Override
    public List<User> getPlayers() {
                return new ArrayList<>(playerMap.values());
    }
}
