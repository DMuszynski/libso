package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.payload.dto.AuthorityDTO;
import pl.dmuszynski.libso.model.User;

import java.util.Set;

@Projection(name = "userAuthoritiesView", types = User.class)
public interface UserAuthoritiesView extends EntityView {
    Set<AuthorityDTO> getAuthorities();
}
