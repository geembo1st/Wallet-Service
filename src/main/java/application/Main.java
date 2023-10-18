package application;

import domen.Admin;
import domen.Player;
import exception.IncorrectValueException;
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

        System.out.println("Выберите действие: " +  "\n  1)регистрация" + "\n  2)вход" + "\n  3)зайти под администратором");
        int action = scanner.nextInt();
        scanner.nextLine();
        switch (action) {
            case(1):
                System.out.println("Придумайте логин:");
                String username = scanner.nextLine();
                System.out.println("Придумайте пароль:");
                String password = scanner.nextLine();
                Player newPlayer = new Player(username, String.valueOf(password).toCharArray());
                playerRepositoryApp.registerPlayer(newPlayer);
                PlayerAction playerAction = new PlayerAction(newPlayer,transactionServiceApp);
                playerAction.action();
                break;
            case(2):
                System.out.print("Введите логин: ");
                String authenticateUsername = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String authenticateUserPassword = scanner.nextLine();
                playerRepositoryApp.authenticatePlayer(authenticateUsername, String.valueOf(authenticateUserPassword).toCharArray());
                break;
            case (3):
                System.out.print("Введите логин: ");
                String adminName = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String adminPassword = scanner.nextLine();
                playerRepositoryApp.authenticatePlayer(adminName, String.valueOf(adminPassword).toCharArray());
                adminAction.action();
                break;
            default:
                throw new IncorrectValueException();
        }
    }
}
