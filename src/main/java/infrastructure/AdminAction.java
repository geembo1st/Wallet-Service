package infrastructure;

import domen.Admin;
import domen.User;
import exception.IncorrectValueException;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("Выберите действие: " + "\n" + "1)Аудит действий игрока " + "\n" + "2)Посмотреть всех игроков");
        int action = scanner.nextInt();
        switch (action) {
            case (1):
                auditService.getAuditLog();
                break;
            case (2):
                List<User> playerList = playerRepository.getPlayers();
                System.out.println(playerList.toString());
                break;
            default:
                throw new IncorrectValueException();
        }
    }
}
