package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.UserAuthoritiesDTO;
import pl.dmuszynski.libso.payload.dto.UserLockedDTO;
import pl.dmuszynski.libso.payload.AuthorizedUserView;

import java.util.Set;

public interface AdminService {
    UserAuthoritiesDTO updateUserAuthoritiesById(UserAuthoritiesDTO userAuthoritiesDetails, Long userId);
    Set<AuthorizedUserView> findAllAuthorizedUserView();
}