package infrastructure;
import domen.Player;

import java.util.Scanner;
public class PlayerAction implements Action {
    Scanner scanner = new Scanner(System.in);
    private TransactionService transactionService;
    private Player player;
    public PlayerAction(Player player, TransactionService transactionService) {
        this.player = player;
        this.transactionService = transactionService;
    }
    public void action() {
        int action;
        do {
            System.out.println("\nВыберите действие:\n\t1)Дебет/снятие средств\n\t2)Кредит\n\t" +
                    "3)Просмотр истории пополнения/снятия средств\n\t4)Узнать баланс\n\t5)Выйти в меню");
            action = Integer.parseInt(scanner.nextLine());
            switch (action) {
                case (1):
                    System.out.println("Введите сумму");
                    int amountDebit = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите id транзакции");
                    String transactionIdDebit = scanner.nextLine();
                    if (transactionService.debit(player, amountDebit, transactionIdDebit)) {
                        System.out.println("Транзакция произведена");
                    }
                    break;
                case (2):
                    System.out.println("Введите сумму");
                    int amountCredit = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите id транзакции");
                    String transactionIdCredit = scanner.nextLine();
                    if (transactionService.credit(player, amountCredit, transactionIdCredit)) {
                        System.out.println("Транзакция произведена");
                    }
                    break;
                case (3):
                    player.getTransactionHistory().forEach((transactionID, amount) -> System.out.println("Transaction: " + transactionID + "\n amount: " + amount));
                    break;
                case (4):
                    long balance = player.getBalance();
                    System.out.println(balance);
                    break;
                case (5):
                    break;
                default:
                    System.out.println("Неверное значение, введите еще раз");
            }
        } while (action != 5);
    }
}