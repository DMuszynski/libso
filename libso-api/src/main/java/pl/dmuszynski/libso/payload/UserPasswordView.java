package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.User;

@Projection(name = "userPasswordView", types = User.class)
public interface UserPasswordView extends EntityView {
    String getPassword();
}
