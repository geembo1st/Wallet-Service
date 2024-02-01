package infrastructure;
import domain.Admin;
import domain.Player;
import domain.TransactionHistory;
import domain.User;
import exception.AuthenticateException;
import exception.RegisterException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.*;

public class PlayerRepositoryApp implements PlayerRepository {
    private Admin admin;
    private AuditService auditService;

    public PlayerRepositoryApp(AuditService auditService, Admin admin) {
        this.admin = admin;
        this.auditService = auditService;
    }

    public void registerPlayer(Player player) {
        if (player.getPassword().length < 6) {
            throw new RegisterException("Пароль должен содержать не менее 6 символов");
        }
            Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            List<String> usernames = session.createQuery("SELECT username FROM Player", String.class).getResultList();
            if (usernames.contains(player.getUsername())) {
                auditService.logAction(player.getUsername(), " регистрация ", false);
                throw new RegisterException("Пользователь с таким логином уже существует");
            } else {
                session.save(player);
                System.out.println("Регистрация прошла успешно");
            auditService.logAction(player.getUsername(), " регистрация ", true);}
                session.getTransaction().commit();
            } finally {
                sessionFactory.close();
            }
        }

    public User authenticatePlayer(String username, char[] password) {
        User user;
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Query<Player> query = session.createQuery(
                    "FROM Player WHERE username = :username AND password = :password", Player.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = query.uniqueResult();
            session.getTransaction().commit();
            if (user != null) {
                auditService.logAction(user.getUsername(), " аутентификация ", true);
                System.out.println("Аутентификация пользователя прошла успешно: " + username);
                return user;
            } else {
                auditService.logAction(username, " аутентификация ", false);
                throw new AuthenticateException("Аутентификация не удалась, не найден пользователь");
            }
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public List<String> getPlayers() {
        List<String> usernames;
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            usernames = session.createQuery("SELECT username FROM Player", String.class).getResultList();
        session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
        return usernames;
    }

    @Override
    public void deletePlayer(Player player) throws Exception {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
                session.delete(player);
                System.out.println("Удаление пользователя прошло успешно");
            auditService.logAction(player.getUsername(), " удаление пользователя ", true);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public long getBalance(long playerId) {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        long balance;
        try {
            session.beginTransaction();
            balance = session.get(Player.class, playerId).getBalance();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            sessionFactory.close();
        }
        return balance;
    }

    @Override
    public User nameChange(Player player,String newUsername) throws Exception {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(TransactionHistory.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            List<String> usernames = session.createQuery("SELECT username FROM Player", String.class).getResultList();
            if (usernames.contains(newUsername)) {
                auditService.logAction(player.getUsername(), " изменение имени ", false);
                throw new Exception("Пользователь с таким логином уже существует");
            } else {
                 player = session.get(Player.class, player.getId());
                player.setUsername(newUsername);
                System.out.println("Изменение имени прошло успешно");
            auditService.logAction(player.getUsername(), " изменение имени ", true);}
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
        return player;
    }
}