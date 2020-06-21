package exceptions;
import java.nio.charset.StandardCharsets;

public class InvalidDataException extends Exception {

    public  InvalidDataException(String message){
        super(new String(message.getBytes(), StandardCharsets.UTF_8));
    }
}
