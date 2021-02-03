package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.User;

@Projection(name = "usernameView", types = User.class)
public interface UsernameView extends EntityView {
    String getUsername();
}
