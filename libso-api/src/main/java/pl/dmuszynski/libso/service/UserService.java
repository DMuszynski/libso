package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.UserSettingsView;
import pl.dmuszynski.libso.payload.dto.UserPasswordDTO;
import pl.dmuszynski.libso.payload.dto.UserEmailDTO;
import pl.dmuszynski.libso.payload.dto.UsernameDTO;

public interface UserService {
    UsernameDTO updateUsernameById(UsernameDTO usernameDetails, Long userId);
    UserEmailDTO updateUserEmailById(UserEmailDTO userEmailDetails, Long userId);
    UserPasswordDTO updateUserPasswordById(UserPasswordDTO userPasswordDetails, Long userId);
    UserSettingsView findUserSettingById(Long userId);
    void deleteUserById(Long userId);
}
