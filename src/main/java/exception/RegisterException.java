package exception;

public class RegisterException extends RuntimeException {
    private static String message = "Пользователь с таким логином уже существует";

    public RegisterException() {
        super(message);
    }

}
