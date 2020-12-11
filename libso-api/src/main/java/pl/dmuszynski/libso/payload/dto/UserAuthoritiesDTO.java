package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.UserAuthoritiesView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class UserAuthoritiesDTO extends AbstractDTO implements UserAuthoritiesView {

    private Set<AuthorityDTO> authorities;

    @Override
    public Set<AuthorityDTO> getAuthorities() {
        return this.authorities;
    }
}
