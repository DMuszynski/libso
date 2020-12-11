package pl.dmuszynski.libso.validator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.DuplicatePasswordException;
import pl.dmuszynski.libso.exception.UniqueUsernameException;
import pl.dmuszynski.libso.exception.UniqueEmailException;
import pl.dmuszynski.libso.model.User;

@Component
public class UserValidator extends AbstractModelValidator<User> {

    private final DuplicatePasswordValidator duplicatePasswordValidator;
    private final UniqueUsernameValidator uniqueUsernameValidator;
    private final UniqueEmailValidator uniqueEmailValidator;

    @Autowired
    public UserValidator(@Qualifier("userRepository") CrudRepository<User, Long> repository, UniqueEmailValidator uniqueEmailValidator,
                         DuplicatePasswordValidator duplicatePasswordValidator, UniqueUsernameValidator uniqueUsernameValidator) {
        super(repository);
        this.uniqueEmailValidator = uniqueEmailValidator;
        this.uniqueUsernameValidator = uniqueUsernameValidator;
        this.duplicatePasswordValidator = duplicatePasswordValidator;
    }

    public void validateUserDuplicatePassword(String newPassword, Long userId) throws DuplicatePasswordException {
        this.duplicatePasswordValidator.validate(newPassword, userId);
    }

    public void validateUserUsername(String username) throws UniqueUsernameException {
        this.uniqueUsernameValidator.validate(username);
    }

    public void validateUserEmail(String email) throws UniqueEmailException {
        this.uniqueEmailValidator.validate(email);
    }
}
