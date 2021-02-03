package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.payload.dto.UserAuthoritiesDTO;
import pl.dmuszynski.libso.payload.AuthorizedUserView;
import pl.dmuszynski.libso.repository.AdminRepository;
import pl.dmuszynski.libso.validator.AdminValidator;
import pl.dmuszynski.libso.service.AuthorityService;
import pl.dmuszynski.libso.service.AdminService;
import pl.dmuszynski.libso.mapper.AuthorityMapper;
import pl.dmuszynski.libso.model.AuthorityType;
import pl.dmuszynski.libso.model.Authority;
import pl.dmuszynski.libso.model.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    private final AuthorityService authorityService;
    private final AdminRepository adminRepository;
    private final AuthorityMapper authorityMapper;
    private final AdminValidator adminValidator;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public UserAuthoritiesDTO updateUserAuthoritiesById(UserAuthoritiesDTO userAuthoritiesDetails, Long userId) {
        this.adminValidator.validateModelAndModelDtoIds(userId, userAuthoritiesDetails.getId());
        final Set<Authority> foundUserAuthorities = userAuthoritiesDetails.getAuthorities().stream()
            .map(this.authorityMapper::mapToModel)
            .map(authority -> this.authorityService
                .findByAuthorityType(AuthorityType.valueOf(authority.getAuthority())))
            .collect(Collectors.toSet());

        final User user = this.entityManager.getReference(User.class, userId);
        user.setAuthorities(foundUserAuthorities);
        user.setLocked(userAuthoritiesDetails.isLocked());

        return userAuthoritiesDetails;
    }

    @Override
    public Set<AuthorizedUserView> findAllAuthorizedUserView() {
        return new HashSet<>(this.adminRepository
            .findAllAuthorizedUserViewBy());
    }
}