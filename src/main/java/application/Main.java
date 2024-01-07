package application;

import domen.Player;
import domen.Transaction_history;
import domen.Username;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class)
                .addAnnotatedClass(Transaction_history.class)

                .addAnnotatedClass(Username.class);


        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Player player = new Player("vova1","petrov1233".toCharArray(),40);
            Username username = new Username(player.getUsername());
            player.setUsernames(username);
            session.save(player);


//            Player player = session.get(Player.class,2);
//            List<Transaction_history> transactions = player.getTransactions();
//            for(Transaction_history transaction_history : transactions) {
//                session.remove(transaction_history);
//            }
//            player.getTransactions().clear();

//           Player player = new Player("azat3", "azat1111".toCharArray(),100);
//           Transaction_history transaction1 = new Transaction_history("transaction1");
//           Transaction_history transaction2 = new Transaction_history("transaction2");
//          //  player.addTransactionToHistory(transaction1);
//            player.addTransactionToHistory(transaction2);
//            session.save(player);

//            Transaction_history history = session.get(Transaction_history.class,3);
//            Player player1 = history.getOwner();
//            System.out.println(player1);
//
//
//            Player player2 = session.get(Player.class,2);
//            Transaction_history new_transaction = new Transaction_history("kino",player2);
//            player2.getTransactions().add(new_transaction);
//            session.save(new_transaction);

//            Player player = session.get(Player.class,2);
//            session.remove(player);
//            player.getTransactions().forEach(i -> i.setOwner(null));

 //           Player player1 = new Player("azat1","azat1234".toCharArray(),100);
//            Player player2 = new Player("azat2","azat1234".toCharArray(),200);
//            Player player3 = new Player("azat3","azat1234".toCharArray(),300);
//            session.save(player1);
//            session.save(player2);
//            session.save(player3);
 //           Player player11 = session.get(Player.class,4);
//            player11.setUsername("azat11");
//            session.delete(player11);
 //             List<Player> playerList = session.createQuery("FROM Player where balance > 150").getResultList();
  //            List<Player> playerList = session.createQuery("FROM Player").getResultList();
 //             session.createQuery("UPDATE Player set username = 'azat22' where id = 3").executeUpdate();
       //       session.createQuery("DELETE Player set username = 'azat22' where id = 3").executeUpdate();
//              for (Player player:playerList) {
//                  System.out.println(player);
//              }
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


//        Scanner scanner = new Scanner(System.in);
//        Admin admin = new Admin();
//        AuditServiceApp auditServiceApp = new AuditServiceApp();
//        PlayerRepositoryApp playerRepositoryApp = new PlayerRepositoryApp(auditServiceApp,admin);
//        TransactionServiceApp transactionServiceApp = new TransactionServiceApp(playerRepositoryApp,auditServiceApp);
//        AdminAction adminAction = new AdminAction(admin,playerRepositoryApp,auditServiceApp);
//        int action;
//        do {
//            System.out.println("\nВыберите действие:\n\t1)регистрация\n\t2)вход\n\t3)зайти под администратором\n\t4) Выйти");
//            action = Integer.parseInt(scanner.nextLine());
//            switch (action) {
//                case(1):
//                    System.out.println("Придумайте логин:");
//                    String username = scanner.nextLine();
//                    System.out.println("Придумайте пароль:");
//                    String password = scanner.nextLine();
//                 //   Player newPlayer = new Player(username, String.valueOf(password).toCharArray());
//                    Player newPlayer = new Player();
//                    try {
//                        playerRepositoryApp.registerPlayer(newPlayer);
//                        PlayerAction playerAction = new PlayerAction(newPlayer, transactionServiceApp);
//                        playerAction.action();
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case(2):
//                    System.out.print("Введите логин: ");
//                    String authenticateUsername = scanner.nextLine();
//                    System.out.print("Введите пароль: ");
//                    String authenticateUserPassword = scanner.nextLine();
//                    try {
//                        User user = playerRepositoryApp.authenticatePlayer(authenticateUsername, String.valueOf(authenticateUserPassword).toCharArray());
//                        PlayerAction playerAction = new PlayerAction((Player) user,transactionServiceApp);
//                        playerAction.action();
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case (3):
//                    System.out.print("Введите логин: ");
//                    String adminName = scanner.nextLine();
//                    System.out.print("Введите пароль: ");
//                    String adminPassword = scanner.nextLine();
//                    try {
//                        playerRepositoryApp.authenticatePlayer(adminName, String.valueOf(adminPassword).toCharArray());
//                        adminAction.action();
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case (4):
//                    break;
//                default:
//                    System.out.println("Неверное значение, введите еще раз");
//            }
//        } while (action != 4);
    }
}

//сделать так чтобы выводил нормально пароль
// сделать баланс большим числом
