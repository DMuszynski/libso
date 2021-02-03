package pl.dmuszynski.libso.service.implementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.payload.UserSettingsView;
import pl.dmuszynski.libso.payload.dto.UserPasswordDTO;
import pl.dmuszynski.libso.payload.dto.UserEmailDTO;
import pl.dmuszynski.libso.payload.dto.UsernameDTO;
import pl.dmuszynski.libso.repository.UserRepository;
import pl.dmuszynski.libso.validator.UserValidator;
import pl.dmuszynski.libso.service.UserService;
import pl.dmuszynski.libso.model.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final EntityManager entityManager;

    @Override
    public UsernameDTO updateUsernameById(UsernameDTO usernameDetails, Long userId) {
        this.userValidator.validateModelAndModelDtoIds(usernameDetails.getId(), userId);
        this.userValidator.validateUserUsername(usernameDetails.getUsername());

        this.userRepository.updateUsernameById(usernameDetails.getUsername(), userId);
        return usernameDetails;
    }

    @Override
    public UserEmailDTO updateUserEmailById(UserEmailDTO userEmailDetails, Long userId) {
        this.userValidator.validateModelAndModelDtoIds(userEmailDetails.getId(), userId);
        this.userValidator.validateUserEmail(userEmailDetails.getEmail());

        this.userRepository.updateEmailById(userEmailDetails.getEmail(), userId);
        return userEmailDetails;
    }

    @Override
    public UserPasswordDTO updateUserPasswordById(UserPasswordDTO userPasswordDetails, Long userId) {
        this.userValidator.validateModelAndModelDtoIds(userPasswordDetails.getId(), userId);
        this.userValidator.validateUserDuplicatePassword(userPasswordDetails.getPassword(), userId);

        this.userRepository.updatePasswordById(this.passwordEncoder
            .encode(userPasswordDetails.getPassword()), userId);
        return userPasswordDetails;
    }

    @Override
    public UserSettingsView findUserSettingById(Long userId) {
        this.userValidator.validateExistModelById(userId);
        return this.userRepository.findUserSettingsById(userId)
            .orElseThrow();
    }

    @Override
    public void deleteUserById(Long userId) {
        this.userValidator.validateExistModelById(userId);
        this.entityManager.remove(this.entityManager.getReference(User.class, userId));
    }
}
