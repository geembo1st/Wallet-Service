package infrastructure;
import java.util.ArrayList;
import java.util.List;
public class AuditServiceApp implements AuditService {
    private List<String> auditLog;
    public AuditServiceApp() {
        this.auditLog = new ArrayList<>();
    }
    @Override
    public void logAction(String username, String action, boolean success) {
        String logEntry = username + " - " + action + " - " + (success ? "SUCCESS" : "FAIL");
        auditLog.add(logEntry);
    }
    public void getAuditLog() {
        System.out.println("Audit Log:");
        for (String entry : auditLog) {
            System.out.println(entry);
        }
    }
}
