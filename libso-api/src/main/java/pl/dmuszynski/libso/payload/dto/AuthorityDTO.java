package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.AuthorityView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class AuthorityDTO extends AbstractDTO implements AuthorityView {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
