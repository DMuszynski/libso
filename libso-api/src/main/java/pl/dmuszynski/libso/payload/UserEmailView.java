package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.User;

@Projection(name = "userEmailView", types = User.class)
public interface UserEmailView extends EntityView {
    String getEmail();
}
