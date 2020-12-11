package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.User;

import java.util.Set;

@Projection(name = "authorizedUserView", types = User.class)
public interface AuthorizedUserView extends EntityView {
    String getEmail();
    String getUsername();
    boolean isLocked();
    boolean isEnabled();
    Set<AuthorityView> getAuthorities();
}
