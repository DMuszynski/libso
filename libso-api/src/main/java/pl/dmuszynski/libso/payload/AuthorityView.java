package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Authority;

@Projection(name = "authorityView", types = Authority.class)
public interface AuthorityView extends EntityView {
    String getAuthority();
}
