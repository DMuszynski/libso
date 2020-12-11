package pl.dmuszynski.libso.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyEnabledException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public AlreadyEnabledException() {
        this("User is already enabled!");
    }

    public AlreadyEnabledException(String message) {
        this(message, null);
    }

    public AlreadyEnabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
