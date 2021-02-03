package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.UserAuthoritiesView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class UserAuthoritiesDTO extends AbstractDTO implements UserAuthoritiesView {

    private boolean locked;
    private Set<AuthorityDTO> authorities;

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public Set<AuthorityDTO> getAuthorities() {
        return this.authorities;
    }
}
