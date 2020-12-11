package pl.dmuszynski.libso.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatePasswordException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public DuplicatePasswordException() {
        this("You enter duplicate password!");
    }

    public DuplicatePasswordException(String message) {
        this(message, null);
    }

    public DuplicatePasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}