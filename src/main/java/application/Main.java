package application;

import domen.Admin;
import domen.Player;
import domen.User;
import infrastructure.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String usernameAdmin = "admin";
        Admin admin = new Admin();
        AuditServiceApp auditServiceApp = new AuditServiceApp();
        PlayerRepositoryApp playerRepositoryApp = new PlayerRepositoryApp(auditServiceApp,admin);
        TransactionServiceApp transactionServiceApp = new TransactionServiceApp(playerRepositoryApp,auditServiceApp);

        System.out.println(playerRepositoryApp.getPlayers());
        System.out.println("Логин:");
        String username = scanner.nextLine();
        System.out.println("Придумайте пароль:");
        String password = scanner.nextLine();
        Player newPlayer = new Player(username, String.valueOf(password).toCharArray());
            playerRepositoryApp.registerPlayer(newPlayer);
        Action action = new PlayerAction(newPlayer,transactionServiceApp);
            action.action();
    }
}
