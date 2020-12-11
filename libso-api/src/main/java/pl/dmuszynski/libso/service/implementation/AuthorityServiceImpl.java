package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.repository.AuthorityRepository;
import pl.dmuszynski.libso.service.AuthorityService;
import pl.dmuszynski.libso.model.AuthorityType;
import pl.dmuszynski.libso.model.Authority;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "roleService")
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository roleRepository;

    @Override
    public Authority findByAuthorityType(AuthorityType authorityType) {
        return roleRepository.findByAuthorityType(authorityType)
            .orElseGet(() -> roleRepository.save(Authority.builder()
                .authorityType(authorityType).build()));
    }
}
