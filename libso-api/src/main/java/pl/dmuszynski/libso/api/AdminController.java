package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.payload.dto.UserAuthoritiesDTO;
import pl.dmuszynski.libso.payload.dto.UserLockedDTO;
import pl.dmuszynski.libso.payload.UserAuthoritiesView;
import pl.dmuszynski.libso.payload.AuthorizedUserView;
import pl.dmuszynski.libso.payload.UserLockedView;
import pl.dmuszynski.libso.service.AdminService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso/users")
@PreAuthorize(value = "hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminService adminService;

    @PatchMapping(value = "/{userId}/authorities")
    public ResponseEntity<UserAuthoritiesView> updateUserAuthoritiesById(@RequestBody UserAuthoritiesDTO userAuthoritiesDetails, @PathVariable Long userId) {
        final UserAuthoritiesDTO updatedUserAuthoritiesDto = this.adminService.updateUserAuthoritiesById(userAuthoritiesDetails, userId);
        return new ResponseEntity<>(updatedUserAuthoritiesDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<AuthorizedUserView>> findAllAuthorizedUserView() {
        final Set<AuthorizedUserView> foundAuthorizedUserDtoList = this.adminService.findAllAuthorizedUserView();
        if (!foundAuthorizedUserDtoList.isEmpty())
            return new ResponseEntity<>(foundAuthorizedUserDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}