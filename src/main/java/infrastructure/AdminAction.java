package infrastructure;
import domain.Admin;
import java.util.List;
import java.util.Scanner;

/**
 * The type Admin action.
 */
public class AdminAction implements Action {
    private PlayerRepository playerRepository;
    private AuditService auditService;
    private Admin admin;

    Scanner scanner = new Scanner(System.in);

    public AdminAction(Admin admin, PlayerRepository playerRepository, AuditService auditService) {
        this.playerRepository = playerRepository;
        this.auditService = auditService;
        this.admin = admin;
    }
    public void action(){
        int action;
        do {
            System.out.println("\nВыберите действие:\n\t1)Аудит действий игрока\n\t2)Посмотреть всех игроков\n\t3)Выйти в меню");
            action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case (1):
                    auditService.getAuditLog();
                    break;
                case (2):
                    List<String> playerList = playerRepository.getPlayers();
                    playerList.forEach(player -> System.out.println(player.toString()));
                    break;
                case (3):
                    break;
                default:
                    System.out.println("Неверное значение, введите еще раз");
            }
        }while(action !=3);
    }
}
