package infrastructure;

import domen.Player;
import exception.IncorrectValueException;
import java.util.Scanner;

public class PlayerAction implements Action {
    Scanner scanner = new Scanner(System.in);
    private TransactionService transactionService;
    private Player player;

    public PlayerAction(Player player, TransactionService transactionService) {
        this.player = player;
        this.transactionService = transactionService;
    }

    public void action()  {
        System.out.println("Выберите действие: " + "\n" + "1)Дебет/снятие средств" + "\n" + "2)Кредит" + "\n"
                + "3)Просмотр истории пополнения/снятия средств" + "\n" + "4)Узнать баланс");
        int action = scanner.nextInt();
        switch (action) {
            case (1):
                System.out.println("Введите сумму");
                int amountDebit = scanner.nextInt();
                System.out.println("Введите id транзакции");
                String transactionIdDebit = scanner.nextLine();
                scanner.nextLine();
                transactionService.debit(player, amountDebit, transactionIdDebit);
                break;
            case (2):
                System.out.println("Введите сумму");
                int amountCredit = scanner.nextInt();
                System.out.println("Введите id транзакции");
                String transactionIdCredit = scanner.nextLine();
                scanner.nextLine();
                transactionService.credit(player, amountCredit, transactionIdCredit);
                break;
            case (3):
                player.getTransactionHistory().forEach(transaction -> System.out.println("Transaction: " + transaction));
                break;
            case (4):
                long balance = player.getBalance();
                System.out.println(balance);
                break;
            default:
                throw new IncorrectValueException();
        }
    }
}