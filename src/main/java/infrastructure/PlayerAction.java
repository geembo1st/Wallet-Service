package infrastructure;
import domen.Player;
import domen.TransactionHistory;

import java.util.Scanner;

/**
 * The type Player action.
 */
public class PlayerAction implements Action {
    /**
     * The Scanner.
     */
    Scanner scanner = new Scanner(System.in);
    private TransactionService transactionService;
    private Player player;
    private PlayerRepository playerRepository;
    private TransactionHistory transactionHistory;

    /**
     * Instantiates a new Player action.
     *
     * @param player             the player
     * @param transactionService the transaction service
     */
    public PlayerAction(Player player, TransactionService transactionService,PlayerRepository playerRepository,TransactionHistory transactionHistory) {
        this.player = player;
        this.transactionService = transactionService;
        this.playerRepository = playerRepository;
        this.transactionHistory = transactionHistory;
    }
    public void action() throws Exception {
        int action;
        do {
            System.out.println("\nВыберите действие:\n\t1)Снятие средств\n\t2)Кредит\n\t" +
                    "3)Просмотр истории пополнения/снятия средств\n\t4)Узнать баланс\n\t5)Изменить имя\n\t" +
                    "6)Удалить профиль\n\t7)Выйти в меню");
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
                    for(TransactionHistory transactions: player.getTransactions()) {
                        System.out.println(transactions.toString());
                    }
                    break;
                case (4):
                    int balance = player.getBalance();
                    System.out.println(balance);
                    break;
                case (5):
                    System.out.println("Введите новое имя");
                    String newUsername = scanner.nextLine();
                    playerRepository.nameChange(player,newUsername);
                    break;
                case (6):
                    playerRepository.deletePlayer(player);
                    break;
                case (7):
                    break;
                default:
                    System.out.println("Неверное значение, введите еще раз");
            }
        } while (action != 7);
    }
}