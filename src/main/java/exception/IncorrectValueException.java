package exception;

public class IncorrectValueException extends RuntimeException{
    private static String message = "Вы ввели некорректное значение ";

    public IncorrectValueException() {
        super(message);
    }
}
