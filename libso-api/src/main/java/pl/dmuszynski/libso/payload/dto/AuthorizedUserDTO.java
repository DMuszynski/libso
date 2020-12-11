package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.AuthorizedUserView;
import pl.dmuszynski.libso.payload.AuthorityView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class AuthorizedUserDTO extends AbstractDTO implements AuthorizedUserView {

    private String email;

    private String username;

    private boolean locked;

    private boolean enabled;

    private Set<AuthorityDTO> authorities;

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Set<AuthorityView> getAuthorities() {
        return this.authorities.stream()
            .map(authorityDTO -> (AuthorityView) authorityDTO)
            .collect(Collectors.toSet());
    }
}
