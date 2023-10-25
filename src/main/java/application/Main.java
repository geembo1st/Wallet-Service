package application;
import domen.Admin;
import domen.Player;
import domen.User;
import infrastructure.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        AuditServiceApp auditServiceApp = new AuditServiceApp();
        PlayerRepositoryApp playerRepositoryApp = new PlayerRepositoryApp(auditServiceApp,admin);
        TransactionServiceApp transactionServiceApp = new TransactionServiceApp(playerRepositoryApp,auditServiceApp);
        AdminAction adminAction = new AdminAction(admin,playerRepositoryApp,auditServiceApp);
        int action;
        do {
            System.out.println("\nВыберите действие:\n\t1)регистрация\n\t2)вход\n\t3)зайти под администратором\n\t4) Выйти");
            action = Integer.parseInt(scanner.nextLine());
            switch (action) {
                case(1):
                    System.out.println("Придумайте логин:");
                    String username = scanner.nextLine();
                    System.out.println("Придумайте пароль:");
                    String password = scanner.nextLine();
                    Player newPlayer = new Player(username, String.valueOf(password).toCharArray());
                    try {
                        playerRepositoryApp.registerPlayer(newPlayer);
                        PlayerAction playerAction = new PlayerAction(newPlayer, transactionServiceApp);
                        playerAction.action();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case(2):
                    System.out.print("Введите логин: ");
                    String authenticateUsername = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String authenticateUserPassword = scanner.nextLine();
                    try {
                        User user = playerRepositoryApp.authenticatePlayer(authenticateUsername, String.valueOf(authenticateUserPassword).toCharArray());
                        PlayerAction playerAction = new PlayerAction((Player) user,transactionServiceApp);
                        playerAction.action();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case (3):
                    System.out.print("Введите логин: ");
                    String adminName = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String adminPassword = scanner.nextLine();
                    try {
                        playerRepositoryApp.authenticatePlayer(adminName, String.valueOf(adminPassword).toCharArray());
                        adminAction.action();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case (4):
                    break;
                default:
                    System.out.println("Неверное значение, введите еще раз");
                    //toDo как сделать чтобы не только при цифрах но и буквах
            }
        } while (action != 4);
    }
}
