package pl.dmuszynski.libso.service.implementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pl.dmuszynski.libso.exception.AlreadyEnabledException;
import pl.dmuszynski.libso.service.CartService;
import pl.dmuszynski.libso.service.RegistrationService;
import pl.dmuszynski.libso.service.AuthorityService;
import pl.dmuszynski.libso.service.TokenService;
import pl.dmuszynski.libso.repository.UserRepository;
import pl.dmuszynski.libso.model.AuthorityType;
import pl.dmuszynski.libso.model.Token;
import pl.dmuszynski.libso.model.User;
import pl.dmuszynski.libso.validator.UserValidator;

import javax.transaction.Transactional;
import java.util.Collections;

@Transactional
@RequiredArgsConstructor
@Service(value = "registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final TokenService tokenService;
    private final CartService cartService;

    @Override
    public void signUp(String email, String username, String password) {
        this.userValidator.validateUserUsername(username);
        this.userValidator.validateUserEmail(email);

        final User registerUser = this.userRepository
            .save(User.builder()
                .email(email)
                .username(username)
                .password(this.passwordEncoder.encode(password))
                .authorities(Collections.singleton(this.authorityService
                    .findByAuthorityType(AuthorityType.ROLE_USER)))
                .build()
            );

        this.cartService.createCart(registerUser);
        this.tokenService.sendToken(registerUser);
    }

    @Override
    public String activateAccountByUserToken(String tokenValue) {
        final Token foundToken = this.tokenService.findByValue(tokenValue);
        final User tokenUser = foundToken.getUser();

        final String activationCorrectMessage = "<h3>Rejestracja przebiegła pomyślnie!</h3>";
        final String backToServiceLink = "<a href='http://localhost:4200'>Przejdź do serwisu</a>";

        if (!tokenUser.isEnabled())
            this.userRepository.activateAccount(tokenUser.getId());
        else
            throw new AlreadyEnabledException();

        return activationCorrectMessage + backToServiceLink;
    }
}
