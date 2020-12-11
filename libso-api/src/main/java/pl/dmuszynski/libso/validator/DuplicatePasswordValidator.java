package pl.dmuszynski.libso.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.DuplicatePasswordException;
import pl.dmuszynski.libso.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DuplicatePasswordValidator {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void validate(String newPassword, Long userId) throws DuplicatePasswordException {
        final String oldPassword = this.userRepository.findUserPasswordById(userId).orElseThrow().getPassword();
        if (this.passwordEncoder.matches(newPassword, oldPassword))
            throw new DuplicatePasswordException();
    }
}