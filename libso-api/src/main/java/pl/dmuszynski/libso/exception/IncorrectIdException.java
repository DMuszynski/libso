package pl.dmuszynski.libso.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncorrectIdException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public IncorrectIdException() {
        this("You enter incorrect id!");
    }

    public IncorrectIdException(String message) {
        this(message, null);
    }

    public IncorrectIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
