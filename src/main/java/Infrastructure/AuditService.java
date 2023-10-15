package Infrastructure;

/**
 * The interface Audit service.
 */
public interface AuditService {
   /**
    * Log action.
    *
    * @param username the username
    * @param action   the action
    * @param success  the success
    */
   void logAction(String username, String action, boolean success);

   void getAuditLog();
}
