package pl.dmuszynski.libso.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class LockedException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public LockedException() {
        this("User is locked!");
    }

    public LockedException(String message) {
        this(message, null);
    }

    public LockedException(String message, Throwable cause) {
        super(message, cause);
    }
}