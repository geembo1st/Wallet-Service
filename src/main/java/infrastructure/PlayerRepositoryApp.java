package infrastructure;

import domen.*;
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
        }
        auditService.logAction(user.getUsername()," регистрация ", true);
        this.playerMap.put(user.getUsername(), user);
        System.out.println("Регистрация прошла успешно");
    }

    public User authenticatePlayer(String userName, char [] password) throws Exception {
        System.out.print("Введите логин: ");
        String authenticateUsername = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String authenticateUserPassword = scanner.nextLine();
        User user = playerMap.get(authenticateUsername);
        if (user != null && String.valueOf(user.getPassword()).equals(authenticateUserPassword) &&
                authenticateUsername.equals(user.getUsername())) {
            System.out.println("Аутентификация пользователя прошла успешно: " + authenticateUsername);
            auditService.logAction(user.getUsername()," аутентификация ", true);
            return user;
        } else {
            auditService.logAction(authenticateUsername," аутентификация ", false);
            throw new Exception("Аутентификация не удалась для пользователя: " + authenticateUsername);
        }
    }

    @Override
    public List<User> getPlayers() {
                return new ArrayList<>(playerMap.values());
    }

}
