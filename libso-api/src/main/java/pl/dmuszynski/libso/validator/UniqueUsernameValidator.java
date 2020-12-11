package pl.dmuszynski.libso.validator;

import pl.dmuszynski.libso.exception.UniqueUsernameException;
import pl.dmuszynski.libso.repository.UserRepository;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UniqueUsernameValidator {

    private final UserRepository userRepository;

    public void validate(String username) throws UniqueUsernameException {
        if (this.userRepository.existsByUsername(username))
            throw new UniqueUsernameException("The user with given username " + username + " is already exist");
    }
}
