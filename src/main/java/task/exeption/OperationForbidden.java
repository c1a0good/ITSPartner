package task.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperationForbidden extends Exception{
    public OperationForbidden(String message) {
        super(message);
    }
}
