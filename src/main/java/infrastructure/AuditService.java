package infrastructure;
public interface AuditService {
   void logAction(String username, String action, boolean success);
   void getAuditLog();
}
