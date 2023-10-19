package exception;

public class AuthenticateException extends RuntimeException {
    private static String message = "Аутентификация не удалась ";

    public AuthenticateException() {super(message);
    }
}
