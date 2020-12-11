package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.User;

@Projection(name = "userLockedView", types = User.class)
public interface UserLockedView extends EntityView {
    boolean isLocked();
}
