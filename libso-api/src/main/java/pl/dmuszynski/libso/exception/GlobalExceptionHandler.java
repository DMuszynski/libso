package pl.dmuszynski.libso.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.auth0.jwt.exceptions.JWTVerificationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.FORBIDDEN, webRequest);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Object> handleJWTVerificationException(JWTVerificationException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.UNAUTHORIZED, webRequest);
    }

    @ExceptionHandler(DuplicatePasswordException.class)
    public ResponseEntity<Object> handleDuplicatePasswordException(DuplicatePasswordException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(AlreadyEnabledException.class)
    public ResponseEntity<Object> handleAlreadyEnabledException(AlreadyEnabledException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(UniqueUsernameException.class)
    public ResponseEntity<Object> handleUniqueUsernameException(UniqueUsernameException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(UniqueEmailException.class)
    public ResponseEntity<Object> handleUniqueEmailException(UniqueEmailException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> handleDisabledException(DisabledException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, new DisabledException("Użytkownik nie jest aktywowany !"), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsExceptionException(BadCredentialsException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, new BadCredentialsException("Podano błędne dane logowania !"), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Object> handleLockedException(LockedException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, new LockedException("Użytkownik jest zablokowany !"), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(IncorrectIdException.class)
    public ResponseEntity<Object> handleIncorrectIdException(IncorrectIdException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }
}
