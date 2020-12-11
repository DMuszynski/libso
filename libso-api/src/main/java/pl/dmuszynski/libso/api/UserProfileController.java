package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.payload.dto.UserPasswordDTO;
import pl.dmuszynski.libso.payload.dto.UserEmailDTO;
import pl.dmuszynski.libso.payload.UserPasswordView;
import pl.dmuszynski.libso.payload.UserEmailView;
import pl.dmuszynski.libso.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "libso/users/{userId}")
public class UserProfileController {

    private final UserService userService;

    @PatchMapping(value = "/email")
    public ResponseEntity<UserEmailView> updateUserEmailById(@RequestBody UserEmailDTO userEmailDetails, @PathVariable Long userId) {
        final UserEmailDTO updatedUserEmailDto = this.userService.updateUserEmailById(userEmailDetails, userId);
        return new ResponseEntity<>(updatedUserEmailDto, HttpStatus.OK);
    }

    @PatchMapping(value = "/password")
    public ResponseEntity<UserPasswordView> updateUserPasswordById(@RequestBody UserPasswordDTO userPasswordDetails, @PathVariable Long userId) {
        final UserPasswordDTO updatedUserPasswordDto = this.userService.updateUserPasswordById(userPasswordDetails, userId);
        return new ResponseEntity<>(updatedUserPasswordDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}