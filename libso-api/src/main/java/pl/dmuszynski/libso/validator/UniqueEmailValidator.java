package pl.dmuszynski.libso.validator;

import pl.dmuszynski.libso.exception.UniqueEmailException;
import pl.dmuszynski.libso.repository.UserRepository;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator {

    private final UserRepository userRepository;

    public void validate(String email) throws UniqueEmailException {
        if (this.userRepository.existsByEmail(email))
            throw new UniqueEmailException("Użytkownik z podanym adresem email już istnieje");
    }
}