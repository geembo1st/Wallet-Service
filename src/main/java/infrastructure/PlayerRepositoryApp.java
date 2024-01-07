package infrastructure;
import domen.Admin;
import domen.Player;
import domen.User;
import exception.AuthenticateException;
import exception.RegisterException;
import lombok.Setter;

import java.sql.*;
import java.util.*;

public class PlayerRepositoryApp implements PlayerRepository {
    private Admin admin;
    private AuditService auditService;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public PlayerRepositoryApp(AuditService auditService, Admin admin) {
        this.admin = admin;
//        this.playerMap = new HashMap<>();
//        this.playerMap.put(admin.getUsername(), admin);
        this.auditService = auditService;
    }

    public void registerPlayer(Player player) {
        if (player.getPassword().length < 6) {
            throw new RegisterException("Пароль должен содержать не менее 6 символов");
        }
        List<String> names = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString("username");
                names.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (names.contains(player.getUsername())) {
                auditService.logAction(player.getUsername(), " регистрация ", false);
                throw new RegisterException("Пользователь с таким логином уже существует");
            } else {
                auditService.logAction(player.getUsername(), " регистрация ", true);
                try {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement("INSERT INTO users VALUES(?,?,?)");
                    preparedStatement.setString(0, player.getUsername());
                    preparedStatement.setString(1, Arrays.toString(player.getPassword()));
                    preparedStatement.setInt(2, player.getBalance());
                    preparedStatement.executeUpdate();
                } catch (SQLException expception) {
                    expception.printStackTrace();
                }
                System.out.println("Регистрация прошла успешно");
            }
        }
    }


    public User authenticatePlayer(String username, char[] password) {
//        User user = playerMap.get(userName);
//        if (user != null && String.valueOf(user.getPassword()).equals(String.valueOf(password)) &&
//                userName.equals(user.getUsername())) {
//            System.out.println("Аутентификация пользователя прошла успешно: " + userName);
//            auditService.logAction(user.getUsername(), " аутентификация ", true);
//            return user;
//        } else {
//            auditService.logAction(userName, " аутентификация ", false);
//            throw new AuthenticateException("Аутентификация не удалась, не найден пользователь");
//            return null;
//        }
        return null;
    }
@Override
    public List<User> getPlayers() {
        List<User> players = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString("username");
                String password = resultSet.getString("password");
                int balance = resultSet.getInt("balance");
                Player player = new Player();
             //   Player player = new Player(name, password.toCharArray());
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}