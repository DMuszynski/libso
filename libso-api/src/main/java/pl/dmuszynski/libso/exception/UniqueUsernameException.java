package pl.dmuszynski.libso.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UniqueUsernameException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public UniqueUsernameException(String message) {
        this(message, null);
    }

    public UniqueUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}