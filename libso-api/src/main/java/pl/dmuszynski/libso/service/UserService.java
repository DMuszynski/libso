package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.UserPasswordDTO;
import pl.dmuszynski.libso.payload.dto.UserEmailDTO;

public interface UserService {
    UserEmailDTO updateUserEmailById(UserEmailDTO userEmailDetails, Long userId);
    UserPasswordDTO updateUserPasswordById(UserPasswordDTO userPasswordDetails, Long userId);
    void deleteUserById(Long userId);
}
