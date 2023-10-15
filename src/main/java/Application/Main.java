package Application;

import Domen.Admin;
import Domen.Player;
import Domen.User;
import Infrastructure.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        AuditServiceApp auditServiceApp = new AuditServiceApp();
        PlayerRepositoryApp playerRepositoryApp = new PlayerRepositoryApp(auditServiceApp,admin);
        AdminAction adminAction = new AdminAction(admin, playerRepositoryApp, auditServiceApp);
        List<User> users = playerRepositoryApp.getPlayers();
        TransactionServiceApp transactionServiceApp = new TransactionServiceApp(playerRepositoryApp,auditServiceApp);
        playerRepositoryApp.getPlayers();
//        System.out.println("Придумайте логин:");
//        String newUsername = scanner.nextLine();
//        System.out.println("Придумайте пароль:");
//        String newPassword = scanner.nextLine();
//        Player newPlayer = new Player(newUsername, String.valueOf(newPassword).toCharArray());
//        try {
//            playerRepositoryApp.registerPlayer(newPlayer);
//        } catch (Exception ignored) {}
//        Action action = new PlayerAction(newPlayer,transactionServiceApp);
//        try {
//            action.action();
//        } catch (Exception ignored) {}
    }
}
