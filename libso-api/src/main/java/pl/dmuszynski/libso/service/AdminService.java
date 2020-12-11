package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.UserAuthoritiesDTO;
import pl.dmuszynski.libso.payload.dto.UserLockedDTO;
import pl.dmuszynski.libso.payload.AuthorizedUserView;

import java.util.Set;

public interface AdminService {
    UserLockedDTO updateUserLockedById(UserLockedDTO userLockedDetails, Long userId);
    UserAuthoritiesDTO updateUserAuthoritiesById(UserAuthoritiesDTO userAuthoritiesDetails, Long userId);
    Set<AuthorizedUserView> findAllAuthorizedUserView(int page, int size, String sortBy);
}