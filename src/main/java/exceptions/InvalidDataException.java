package exceptions;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class InvalidDataException extends Exception {

    public  InvalidDataException(String message)  {
        super(message);
    }
}
