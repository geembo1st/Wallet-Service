package infrastructure;

import domen.Admin;
import domen.User;
import exception.AuthenticateException;
import exception.RegisterException;

import java.util.*;


public class PlayerRepositoryApp implements PlayerRepository {
    Scanner scanner = new Scanner(System.in);
    private Admin admin;
    private Map<String, User> playerMap;
    private AuditService auditService;

    public PlayerRepositoryApp(AuditService auditService, Admin admin) {
        this.admin=admin;
        this.playerMap = new HashMap<>();
        this.playerMap.put(admin.getUsername(), admin);
        this.auditService = auditService;
    }

    public void registerPlayer(User user)  {
        if (playerMap.containsKey(user.getUsername())) {
            auditService.logAction(user.getUsername()," регистрация ", false);
            throw new RegisterException();
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
            throw new AuthenticateException();
        }
    }

    @Override
    public List<User> getPlayers() {
                return new ArrayList<>(playerMap.values());
    }

}
